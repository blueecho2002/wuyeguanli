<template>
    <el-container>
        <el-header style="text-align: right; font-size: 12px">
            <div
                style="height: 60px; background-color: #fff; display: flex; align-items: center; border-bottom: 1px solid #ddd">
                <div style="flex: 1">
                    <div style="padding-left: 20px; display: flex; align-items: center">
                        <!-- <img src="" alt="" style="width: 40px"> -->
                        <div style="font-weight: bold; font-size: 24px; margin-left: 5px">小区物业管理系统</div>
                    </div>
                </div>
                <div style="width: fit-content; padding-right: 10px; display: flex; align-items: center;">
                    <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" alt=""
                        style="width: 40px; height: 40px ;padding-right:10px;">

                    <el-dropdown :hide-on-click="false">
                        <span class="el-dropdown-link">
                            管理员<el-icon class="el-icon--right"><arrow-down /></el-icon>
                        </span>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item>退出登录</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>

                </div>
            </div>
        </el-header>
        <el-container>
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-menu :default-openeds="['1', '3']">
                    <el-submenu index="1">
                        <template slot="title"><i class="el-icon-message"></i>小区业务管理</template>
                        <el-menu-item-group>
                            <router-link to="/repair">
                                <el-menu-item index="1-1">设备维修管理</el-menu-item>
                            </router-link>
                            <router-link to="/park">
                                <el-menu-item index="1-2">小区停车位管理</el-menu-item>
                            </router-link>
                            <router-link to="/complain">
                                <el-menu-item index="1-3">住户投诉管理</el-menu-item>
                            </router-link>
                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
            </el-aside>
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
                            start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd"></el-date-picker>
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
                    <el-table-column prop="status" label="状态">
                    </el-table-column>
                    <el-table-column prop="createTime" label="创建时间">
                    </el-table-column>
                    <el-table-column prop="updateTime" label="更新时间">
                    </el-table-column>

                    <el-table-column label="操作" width="300px" style="text-align: center;">
                        <template slot-scope="scope">
                            <el-button size="mini" type="primary" @click="editRow(scope.row)">编辑</el-button>
                            <el-button size="mini" type="danger" @click="deleteRow(scope.row)">删除</el-button>
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


                <el-dialog title="编辑投诉信息" :visible.sync="updateFormVisible" style="width: 1500px;">
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
            form: {
                description: '',
                name: '',
                gender: '',
                phoneNumber: '',
                status: ''
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
        updateComplaint() {
            // 发送请求以使用更新后的表单数据更新学员
            axios.put(`http://localhost:8082/complain/updateComplaint/${this.form.id}`, this.form, {
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
            axios.delete(`http://localhost:8082/complain/deleteComplainById/` + row.id)
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
            axios.post('http://localhost:8082/student/insertStudent', this.form, {
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

            const url = 'http://localhost:8082/student/disciplineStudent';

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
            axios.get("http://localhost:8082/complain/selectComplains/" + this.pagination.currentPage + '/' + this.pagination.pageSize + param).then((res) => {
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

            axios.post('http://localhost:8082/complain/batchDeleteComplaints', deleteIds)
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