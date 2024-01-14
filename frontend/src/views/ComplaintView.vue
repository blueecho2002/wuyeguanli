<template>
    <el-container>
        <el-container>

            <el-main>
                <div class="filter-container" style="display: flex; justify-content: space-between;height: 50px;">
                    <div>
                        <label>投诉人姓名</label>
                        &ensp;
                        <el-input placeholder="请输入内容" v-model="pagination.name" clearable style="width: 200px;" />
                    </div>
                    <div>
                        <label>投诉信息</label>
                        &ensp;
                        <el-input placeholder="请输入内容" v-model="pagination.description" clearable style="width: 200px;" />
                    </div>
                    <div class="block">
                        <el-date-picker v-model="pagination.date" type="daterange" range-separator="至"
                            start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD"></el-date-picker>
                    </div>
                    <el-button type="primary" @click="getAll()" class="dalfBut">查询</el-button>
                </div>
                <!-- </div> -->
                <div style="display: flex; justify-content: flex-start; gap: 10px; ">
                    <el-button type="primary" @click="batchDelete()">+批量删除</el-button>
                </div>

                <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%"
                    @selection-change="handleSelectionChange" class="custom-table">
                    <el-table-column type="selection" width="55">
                    </el-table-column>
                    <el-table-column prop="id" label="id" width="80">
                    </el-table-column>
                    <el-table-column prop="description" label="投诉信息" width="140">
                    </el-table-column>
                    <el-table-column prop="name" label="投诉人姓名" width="120">
                    </el-table-column>
                    <el-table-column prop="gender" label="投诉人性别">
                    </el-table-column>
                    <el-table-column prop="phoneNumber" label="投诉人电话">
                    </el-table-column>
                    <el-table-column prop="personnelName" label="物业人员姓名" width="120">
                    </el-table-column>
                    <el-table-column prop="personnelPhoneNumber" label="物业人员电话" width="120">
                    </el-table-column>
                    <el-table-column prop="status" label="状态">
                    </el-table-column>
                    <el-table-column prop="createTime" label="创建时间">
                    </el-table-column>
                    <el-table-column prop="updateTime" label="更新时间">
                    </el-table-column>

                    <el-table-column label="操作" width="300px" style="text-align: center;">
                        <template #default="scope">
                            <el-button  type="primary"
                                @click="distributionRow(scope.row), fetchPersonnels()">分配物业人员</el-button>
                            <el-button  type="primary" @click="editRow(scope.row)">编辑</el-button>
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


                <el-dialog title="编辑投诉信息" v-model="updateFormVisible" style="width: 1500px;">
                    <el-form :model="form">
                        <el-form-item label="投诉信息" :label-width="formLabelWidth">
                            <el-input v-model="form.description" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="姓名" :label-width="formLabelWidth">
                            <el-input v-model="form.name" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="性别" :label-width="formLabelWidth">
                            <el-select v-model="form.gender" placeholder="请选择性别">
                                <el-option label="男" value="男"></el-option>
                                <el-option label="女" value="女"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="手机号" :label-width="formLabelWidth">
                            <el-input v-model="form.phoneNumber" autocomplete="off" maxlength="11"></el-input>
                        </el-form-item>
                        <el-form-item label="状态" :label-width="formLabelWidth">
                            <el-select v-model="form.status" placeholder="请选择状态">
                                <el-option label="未受理" value="0"></el-option>
                                <el-option label="已受理" value="1"></el-option>
                                <el-option label="已处理完毕" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="updateFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="updateFormVisible = false, updateComplaint()">更 改</el-button>
                    </div>
                </el-dialog>

                <el-dialog title="分配业务人员" v-model="distributionFormVisible" style="width: 1500px;">
                    <el-form :model="personnalId">
                        <el-form-item label="物业人员" :label-width="formLabelWidth">
                            <el-select v-model="personnalId" placeholder="请选择物业人员" style="width: 500px;">
                                <el-option v-for="personnel in personnels" :key="personnel.id"
                                    :label="`${personnel.id} - ${personnel.name}`" :value="personnel.id"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="distributionFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="distributionFormVisible = false, updatePersonnel()">确
                            定</el-button>
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
            dialogFormVisible: false,
            disciplineVisible: false,
            updateFormVisible: false,
            deleteVisible: false,
            distributionFormVisible: false,
            personnels: [],
            form: {
                description: '',
                name: '',
                gender: '',
                phoneNumber: '',
                status: ''
            },
            personnalId: '',
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
                description: '',
                date: [] // 日期范围
            },
            selectedRows: [], // 数组用于存储选定的行
        }
    },
    created() {
        // 页面加载时自动查询学生数据
        // this.fetchStudentData();
        this.getAll();
        this.fetchPersonnels();
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
        distributionRow(row) {
            this.form = { ...row };
            this.distributionFormVisible = true;
        },
        fetchPersonnels() {
            // 异步请求获取物业人员列表
            axios.get('http://localhost:8081/repair/selectPersonnel')
                .then(response => {
                    this.personnels = response.data.data;
                })
                .catch(error => {
                    console.error('获取物业人员列表失败:', error);
                });
        },
        //分配物业人员
        updatePersonnel() {
            // 确保 id 和 personnelId 不为空
            if (!this.form.id || !this.personnalId) {
                console.error('id 或 personnelId 为空');
                return;
            }

            // 构建请求的数据对象
            const data = {
                id: this.form.id,
                personnelId: this.personnalId,
            };

            // 发送请求
            axios.post('http://localhost:8081/complain/updatePersonnel/', data)
                .then(response => {
                    console.log('物业人员分配成功:', response.data);
                    // 显示成功的提示
                    this.$message.success('物业人员分配成功');
                    this.getAll();
                })
                .catch(error => {
                    console.error('物业人员分配失败:', error);

                    // 显示失败的提示
                    this.$message.error('物业人员分配失败');
                })
                .finally(() => {
                    // 关闭分配业务人员对话框
                    this.distributionFormVisible = false;
                });
        }
        ,
        // 处理学员更新的方法
        updateComplaint() {
            // 发送请求以使用更新后的表单数据更新学员
            axios.post(`http://localhost:8081/complain/updateComplaint/${this.form.id}`, this.form, {
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => {
                    // 处理成功更新
                    console.log('更新成功:', response.data);
                    // 调用重新查询数据的方法
                    this.getAll();

                    // 显示更新成功的提示
                    this.$message.success('更新成功');
                })
                .catch(error => {
                    console.error('更新学员时发生错误:', error);
                    // 处理更新失败
                    // 处理更新失败的情况adddisciplinediscipline
                    // 显示更新失败的提示
                    this.$message.error('更新失败');
                })
                .finally(() => {
                    // 关闭更新学员对话框
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
            axios.delete(`http://localhost:8081/complain/deleteComplainById/` + row.id)
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
                    this.$message.success('删除失败');
                });
        },
        addStudent() {
            // 在这里调用后端接口
            axios.post('http://localhost:8081/student/insertStudent', this.form, {
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => {
                    // 处理添加成功的情况
                    console.log('学员添加成功:', response.data);
                    // 在前端更新UI，例如将新的学员数据加入到tableData数组
                    this.tableData.push(response.data.data);
                    this.$message.success('添加成功');
                })
                .catch(error => {
                    console.error('添加学员时发生错误:', error);
                    // 处理添加失败的情况
                    this.$message.success('添加失败');
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

            if (this.pagination.description !== undefined && this.pagination.description !== null) {
                param += "&description=" + this.pagination.description;
            }

            if (this.pagination.date !== undefined && this.pagination.date !== null) {
                param += "&date1=" + this.pagination.date[0];
                param += "&date2=" + this.pagination.date[1];
            }


            // 发送异步请求
            axios.get("http://localhost:8081/complain/selectComplains/" + this.pagination.currentPage + '/' + this.pagination.pageSize + param).then((res) => {
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

            axios.post('http://localhost:8081/complain/batchDeleteComplaints', deleteIds)
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
                });
        },

    },


};
</script>