<template>
    <el-container>
        <el-container>
            <el-main>
                <div class="filter-container" style="display: flex; justify-content: space-between;height: 50px;">
                    <div>
                        <label>所有人姓名</label>
                        &ensp;
                        <el-input placeholder="请输入内容" v-model="pagination.name" clearable style="width: 200px;" />
                    </div>
                    <div>
                        <label>所有人性别</label>
                        &ensp;
                        <el-select v-model="pagination.gender" placeholder="请选择性别" clearable style="width: 200px;">
                            <el-option label="男" value="男"></el-option>
                            <el-option label="女" value="女"></el-option>
                        </el-select>
                    </div>


                    <div class="block">
                        <el-date-picker v-model="pagination.date" type="daterange" range-separator="至"
                            start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD"></el-date-picker>
                    </div>

                    <el-button type="primary" @click="getAll()" class="dalfBut">查询</el-button>
                </div>
                <!-- </div> -->
                <div style="display: flex; justify-content: flex-start; gap: 10px; ">
                    <el-button type="primary" @click="dialogFormVisible = true, fetchAllParking()">+分配车位</el-button>
                    <el-button type="primary" @click="batchDelete()">+批量删除</el-button>
                </div>

                <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%"
                    @selection-change="handleSelectionChange" class="custom-table">
                    <el-table-column type="selection" width="55">
                    </el-table-column>
                    <el-table-column prop="id" label="停车位号" width="100">
                    </el-table-column>
                    <el-table-column prop="name" label="所有人姓名">
                    </el-table-column>
                    <el-table-column prop="gender" label="所有人性别">
                    </el-table-column>
                    <el-table-column prop="phoneNumber" label="所有人电话">
                    </el-table-column>
                    <el-table-column prop="createTime" label="创建时间">
                    </el-table-column>
                    <el-table-column prop="updateTime" label="更新时间">
                    </el-table-column>

                    <el-table-column label="操作" width="300px" style="text-align: center;">
                        <template #default="scope">
                            <el-button  type="primary" @click="editRow(scope.row)">修改车位分配</el-button>
                            <el-button  type="danger" @click="deleteRow(scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!--分页组件-->
                <div class="pagination-container">
                    <el-pagination class="pagiantion" @size-change="handleSizeChange" @current-change="handleCurrentChange"
                        :current-page="pagination.currentPage" :page-sizes="[10, 20, 50, 100]"
                        :page-size="pagination.pageSize" layout="total, sizes, prev, pager, next, jumper"
                        :total="pagination.total">
                    </el-pagination>
                </div>

                <el-dialog title="添加车位信息" v-model="dialogFormVisible" style="width: 1500px;">
                    <el-form :model="form">
                        <el-form-item label="车位号" :label-width="formLabelWidth">
                            <el-select v-model="form.id" placeholder="请选择车位号" style="width: 500px;">
                                <el-option v-for="parking in allParking" :key="parking.id" :label="parking.id"
                                    :value="parking.id"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="所有人" :label-width="formLabelWidth">
                            <el-select v-model="form.ownerId" placeholder="请选择所有人" style="width: 500px;">
                                <el-option v-for="person in allPersons" :key="person.id"
                                    :label="`${person.id} - ${person.name}`" :value="person.id"></el-option>
                            </el-select>
                        </el-form-item>

                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="dialogFormVisible = false, addPark()">添 加</el-button>
                    </div>
                </el-dialog>

                <el-dialog title="编辑投诉信息" v-model="updateFormVisible" style="width: 1500px;">
                    <el-form :model="form">
                        <el-form-item label="车位号" :label-width="formLabelWidth">
                            <el-select v-model="form.id" placeholder="请选择车位号" style="width: 500px;" :disabled="true">
                                <el-option v-for="parking in allParking" :key="parking.id" :label="parking.id"
                                    :value="parking.id"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="所有人" :label-width="formLabelWidth">
                            <el-select v-model="form.ownerId" placeholder="请选择所有人" style="width: 500px;">
                                <el-option v-for="person in allPersons" :key="person.id"
                                    :label="`${person.id} - ${person.name}`" :value="person.id"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="updateFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="updateFormVisible = false, updatePark()">更 改</el-button>
                    </div>
                </el-dialog>
            </el-main>

        </el-container>

    </el-container>
</template>

<style>
.el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
}

.el-aside {
    color: #333;
}

a {
    text-decoration: none;
}

.custom-table {
    border-collapse: collapse;
    width: 100%;
}

.custom-table th,
.custom-table td {
    padding: 8px;
    text-align: left;
}

.custom-table thead th {
    background-color: #f2f2f2;
}

.custom-table tbody tr:nth-child(odd) {
    background-color: #f9f9f9;
}

.custom-table tbody tr:hover {
    background-color: #e0e0e0;
}

.custom-table tbody tr.is-selected {
    background-color: #c2e2c2;
}

.custom-table .el-pagination {
    margin-top: 10px;
}
</style>


<script>


import axios from 'axios';

export default {
    data() {
        return {
            tableData: [],
            allPersons: [], // 存储所有人员的列表
            allParking: [],
            dialogFormVisible: false,
            disciplineVisible: false,
            updateFormVisible: false,
            deleteVisible: false,
            form: {
                id: '',
                ownerId: '',
            },
            discipline: {
                score: ''
            },
            delete: {
                deleteId: ''
            },
            pagination: {//分页相关模型数据
                currentPage: 1,//当前页码
                pageSize: 10,//每页显示的记录数
                total: 0,//总记录数
                //需要根据查询的条件
                name: '',
                gender: '',
                date: [] // 日期范围
            },
            selectedRows: [], // 数组用于存储选定的行
        }
    },
    created() {
        // 页面加载时自动查询学生数据
        // this.fetchStudentData();
        this.getAll();
        this.fetchAllPersons(); // 获取所有人员数据
        this.fetchAllParking();
    },
    methods: {
        handleSizeChange(pageSize) {
            this.pagination.pageSize = pageSize;
            this.getAll();
        },
        handleCurrentChange(currentPage) {
            //修改页码值为当前选中的页码值
            this.pagination.currentPage = currentPage;
            //执行查询
            this.getAll();
        },

        editRow(row) {
            // 使用选定行的值设置表单数据
            this.form = { ...row };

            // 显示更新学员对话框
            this.updateFormVisible = true;
        },
        // 处理学员更新的方法
        updatePark() {

            axios.post(`http://localhost:8081/park/updatePark`, this.form, {
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => {
                    // 处理成功更新
                    console.log('车位信息更新成功:', response.data);
                    // 调用重新查询数据的方法
                    this.getAll();

                    // 显示更新成功的提示
                    this.$message.success('车位信息更新成功');
                })
                .catch(error => {
                    console.error('更新车位信息时发生错误:', error);
                    // 处理更新失败
                    // 处理更新失败的情况
                    // 显示更新失败的提示
                    this.$message.error('车位信息更新失败');
                })
                .finally(() => {
                    // 关闭更新车位信息对话框
                    this.updateFormVisible = false;
                });
        },

        deleteRow(row) {
            // 显示删除确认对话框，并传递行数据
            this.$confirm('您确定要删除该学员信息吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            })
                .then(() => {
                    // 处理删除逻辑，使用行数据
                    this.handleDelete(row);
                })
                .catch(() => {
                    // 用户取消了删除操作
                });
        },
        handleDelete(row) {
            // 在这里可以调用后端API删除学员
            axios.delete(`http://localhost:8081/park/deleteParkById/` + row.id)
                .then(response => {
                    // 处理删除成功的情况，可以更新前端UI，例如移除对应的行
                    // 实现真正的删除逻辑，从 tableData 数组中删除指定的行数据
                    const index = this.tableData.indexOf(row);
                    if (index !== -1) {
                        this.tableData.splice(index, 1);
                    }
                    this.$message.success('删除成功');
                })
                .catch(error => {
                    console.error('删除学员时发生错误:', error);
                    // 处理删除失败的情况
                    this.$message.error('删除失败');
                });
        },
        fetchAllParking() {
            // 从服务器获取所有人员数据
            axios.get("http://localhost:8081/park/selectPark").then((res) => {
                this.allParking = res.data.data; // 假设响应包含人员数组
                console.log(this.allPersons);
            });
        },
        fetchAllPersons() {
            // 从服务器获取所有人员数据
            axios.get("http://localhost:8081/park/selectOwner").then((res) => {
                this.allPersons = res.data.data; // 假设响应包含人员数组
                console.log(this.allParking);
            });
        },
        addPark() {
            axios.post('http://localhost:8081/park/insertPark', this.form, {
                headers: {
                    'Content-Type': 'application/json',
                },
            }).then(response => {
                if (response.data.code == 200) {
                    // 处理添加成功的情况
                    console.log('车位分配成功:', response.data);
                    // 在前端更新UI，例如将新的学员数据加入到tableData数组
                    this.tableData.push(response.data.data);
                    this.$message.success('车位分配成功');
                    this.getAll();
                } else {
                    // 处理添加失败的情况
                    console.error('车位已有所有人：', response.data);
                    this.$message.error("车位已有所有");  // Display the error message from the backend
                }
            }).catch(error => {
                console.error('车位已有所有人：', error);
                // 处理添加失败的情况
                this.$message.error('车位已有所有人');
            })
                .finally(() => {
                    // 关闭添加学员对话框
                    this.dialogFormVisible = false;
                });
        },

        prepareForDiscipline(row) {
            // 设置 form 的 id 和 discipline 的 score
            this.form.id = row.id;
            this.discipline.score = ''; // 或者你可以设置默认值

            // 打开违纪对话框
            this.disciplineVisible = true;
        },

        handleDiscipline() {
            console.log('Form ID:', this.form.id);
            console.log('Discipline Score:', this.discipline.score);

            const url = 'http://localhost:8081/student/disciplineStudent';

            // 确保将'id'作为请求主体的一部分，而不是URL参数
            const data = {
                id: this.form.id,
                disciplinaryViolationsScore: this.discipline.score
            };

            axios.post(url, data)
                .then(response => {
                    console.log('违纪扣分成功:', response.data);
                    this.getAll();
                    this.$message.success('违纪扣分成功');
                })
                .catch(error => {
                    console.error('违纪扣分时发生错误:', error);
                    this.$message.error('违纪扣分失败');
                })
                .finally(() => {
                    this.disciplineVisible = false;
                });
        },
        getAll() {
            // 组织参数，拼接url请求地址
            let param = "";

            if (this.pagination.name !== undefined && this.pagination.name !== null) {
                param += "?name=" + this.pagination.name;
            }

            if (this.pagination.gender !== undefined && this.pagination.gender !== null) {
                param += "&gender=" + this.pagination.gender;
            }

            if (this.pagination.date !== undefined && this.pagination.date !== null) {
                param += "&date1=" + this.pagination.date[0];
                param += "&date2=" + this.pagination.date[1];
            }


            // 发送异步请求
            axios.get("http://localhost:8081/park/selectPark/" + this.pagination.currentPage + '/' + this.pagination.pageSize + param).then((res) => {
                this.pagination.pageSize = res.data.data.size;
                this.pagination.currentPage = res.data.data.current;
                this.pagination.total = res.data.data.total;
                // 处理状态列显示
                this.tableData = res.data.data.records.map(record => {
                    // 将状态值转换为对应的文本
                    record.status = record.status === '0' ? '未受理' : (record.status === '1' ? '已受理' : '已处理完毕');
                    return record;
                });
            });
        },
        handleSelectionChange(selection) {
            this.selectedRows = selection;
        },
        batchDelete() {
            if (this.selectedRows.length === 0) {
                this.$message.warning('请选择要删除的行');
                return;
            }

            this.$confirm('您确定要删除选定的学员信息吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            })
                .then(() => {
                    // 处理批量删除逻辑，使用选定的行数据
                    this.handleBatchDelete(this.selectedRows);
                })
                .catch(() => {
                    // 用户取消了删除操作
                });
        },
        handleBatchDelete(selectedRows) {
            // 在这里可以调用后端API执行批量删除
            const deleteIds = selectedRows.map(row => row.id);

            axios.post('http://localhost:8081/park/batchDeleteParks', deleteIds)
                .then(response => {
                    // 处理批量删除成功的情况
                    console.log('批量删除成功:', response.data);

                    // 更新前端UI，从tableData数组中移除选定的行数据
                    this.tableData = this.tableData.filter(row => !selectedRows.includes(row));

                    this.$message.success('批量删除成功');
                })
                .catch(error => {
                    console.error('批量删除时发生错误:', error);
                    // 处理批量删除失败的情况
                    this.$message.error('批量删除失败');
                })
                .finally(() => {
                    // 可选的清理工作，如果有的话
                });
        },


    },


};
</script>