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
                <div style="width: 200px; border-right: 1px solid #ddd; min-height: calc(100vh - 60px)">
                    <el-menu router style="border: none">
                        <el-menu-item index="/home">
                            <el-icon>
                                <HomeFilled />
                            </el-icon>
                            <span>系统首页</span>
                        </el-menu-item>
                        <el-sub-menu index="2">
                            <template #title>
                                <el-icon>
                                    <Money />
                                </el-icon>
                                <span>收费管理</span>
                            </template>
                            <el-menu-item index="/about">
                                <el-icon>
                                    <List />
                                </el-icon>
                                <span>收费明细</span>
                            </el-menu-item>
                            <el-menu-item index="/person">
                                <el-icon>
                                    <Files />
                                </el-icon>
                                <span>收费项目管理</span>
                            </el-menu-item>
                            <el-menu-item index="/person">
                                <el-icon>
                                    <Ticket />
                                </el-icon>
                                <span>预付款管理</span>
                            </el-menu-item>
                        </el-sub-menu>
                        <el-menu-item index="/person">
                            <el-icon>
                                <ChatLineSquare />
                            </el-icon>
                            <span>住户投诉管理</span>
                        </el-menu-item>
                        <el-menu-item index="/person">
                            <el-icon>
                                <SuitcaseLine />
                            </el-icon>
                            <span>小区维修管理</span>
                        </el-menu-item>
                        <el-menu-item index="/person">
                            <el-icon>
                                <Van />
                            </el-icon>
                            <span>小区车位管理</span>
                        </el-menu-item>
                        <el-menu-item index="/person">
                            <el-icon>
                                <User />
                            </el-icon>
                            <span>住户管理</span>
                        </el-menu-item>
                        <el-menu-item index="/person">
                            <el-icon>
                                <Tickets />
                            </el-icon>
                            <span>物业资料管理</span>
                        </el-menu-item>
                        <el-menu-item index="/person">
                            <el-icon>
                                <Clock />
                            </el-icon>
                            <span>计量仪表管理</span>
                        </el-menu-item>
                        <el-menu-item index="login">
                            <el-icon>
                                <SwitchButton />
                            </el-icon>
                            <span>退出系统</span>
                        </el-menu-item>
                    </el-menu>
                </div>
            </el-aside>
            <el-main>
                <div class="filter-container" style="display: flex; justify-content: space-between;height: 50px;">
                    <div>
                        <label>房屋ID</label>
                        &ensp;
                        <el-select v-model="pagination.houseId" placeholder="请选择房屋id" clearable style="width: 200px;">
                            <el-option v-for="id in houseIds" :key="id" :label="id" :value="id"></el-option>
                        </el-select>
                    </div>
                    <div>
                        <label>设备名称</label>
                        &ensp;
                        <el-input placeholder="请输入内容" v-model="pagination.serviceName" clearable style="width: 200px;" />
                    </div>
                    <div>
                        <label>报修人姓名</label>
                        &ensp;
                        <el-input placeholder="请输入内容" v-model="pagination.name" clearable style="width: 200px;" />
                    </div>
                    <div>
                        <label>物业人员姓名</label>
                        &ensp;
                        <el-input placeholder="请输入内容" v-model="pagination.personnelName" clearable style="width: 200px;" />
                    </div>
                </div>
                <div class="filter-container" style="display: flex; justify-content: space-between;height: 50px;">
                    <div>
                        <label>状态</label>
                        &ensp;
                        <el-select v-model="pagination.status" placeholder="请选择状态" clearable style="width: 200px;">
                            <el-option label="未受理" value="0"></el-option>
                            <el-option label="已受理" value="1"></el-option>
                            <el-option label="已处理完毕" value="2"></el-option>
                        </el-select>
                    </div>
                    <div class="block">
                        <label>报修日期</label>
                        &ensp;
                        <el-date-picker v-model="pagination.date" type="daterange" range-separator="至"
                            start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd"></el-date-picker>
                    </div>

                    <el-button type="primary" @click="getAll()" class="dalfBut">查询</el-button>
                </div>
                <!-- </div> -->
                <div style="display: flex; justify-content: flex-start; gap: 10px; ">
                    <el-button type="primary"
                        @click="dialogFormVisible = true, fetchAllPersons(), fetchHouseIds(), fetchPersonnels()">+添加维修任务</el-button>
                    <el-button type="primary" @click="batchDelete()">+批量删除</el-button>
                </div>

                <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%"
                    @selection-change="handleSelectionChange" class="custom-table">
                    <el-table-column type="selection" width="55">
                    </el-table-column>
                    <el-table-column prop="id" label="id" width="100">
                    </el-table-column>
                    <el-table-column prop="description" label="报修信息">
                    </el-table-column>
                    <el-table-column prop="houseId" label="房屋id" width="100">
                    </el-table-column>
                    <el-table-column prop="deviceName" label="设备名称">
                    </el-table-column>
                    <el-table-column prop="name" label="报修人姓名" width="120">
                    </el-table-column>
                    <el-table-column prop="phoneNumber" label="报修人姓名电话" width="120">
                    </el-table-column>
                    <el-table-column prop="personnelName" label="物业人员姓名" width="120">
                    </el-table-column>
                    <el-table-column prop="phoneNumber" label="物业人员电话" width="120">
                    </el-table-column>
                    <el-table-column prop="status" label="状态" width="120">
                    </el-table-column>
                    <el-table-column prop="createTime" label="创建时间">
                    </el-table-column>
                    <el-table-column prop="updateTime" label="更新时间">
                    </el-table-column>

                    <el-table-column label="操作" width="300px" style="text-align: center;">
                        <template slot-scope="scope">
                            <el-button size="mini" type="primary"
                                @click="editRow(scope.row), fetchPersonnels()">分配业务人员</el-button>
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

                <el-dialog title="添加维修信息" :visible.sync="dialogFormVisible" style="width: 1500px;">
                    <el-form :model="form">
                        <el-form-item label="维修信息" :label-width="formLabelWidth">
                            <el-input v-model="form.description" autocomplete="off" style="width: 500px;"></el-input>
                        </el-form-item>
                        <el-form-item label="房屋id" :label-width="formLabelWidth">
                            <el-select v-model="form.houseId" placeholder="请选择房屋id" style="width: 500px;"
                                @change="loadDevices">
                                <el-option v-for="id in houseIds" :key="id" :label="id" :value="id"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="设备" :label-width="formLabelWidth">
                            <el-select v-model="form.deviceId" placeholder="请选择设备" style="width: 500px;">
                                <el-option v-for="device in devices" :key="device.id"
                                    :label="`${device.id} - ${device.name}`" :value="device.id"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="报修人" :label-width="formLabelWidth">
                            <el-select v-model="form.ownerId" placeholder="请选择报修人" style="width: 500px;">
                                <el-option v-for="person in allPersons" :key="person.id"
                                    :label="`${person.id} - ${person.name}`" :value="person.id"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="物业人员" :label-width="formLabelWidth">
                            <el-select v-model="form.personnelId" placeholder="请选择设备" style="width: 500px;">
                                <el-option v-for="personnel in personnels" :key="personnel.id"
                                    :label="`${personnel.id} - ${personnel.name}`" :value="personnel.id"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="dialogFormVisible = false, addRepair()">添 加</el-button>
                    </div>
                </el-dialog>


                <el-dialog title="分配业务人员" :visible.sync="updateFormVisible" style="width: 1500px;">
                    <el-form :model="form">
                        <el-form-item label="物业人员" :label-width="formLabelWidth">
                            <el-select v-model="form.personnelId" placeholder="请选择物业人员" style="width: 500px;">
                                <el-option v-for="personnel in personnels" :key="personnel.id"
                                    :label="`${personnel.id} - ${personnel.name}`" :value="personnel.id"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="updateFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="updateFormVisible = false, updateRepair()">确 定</el-button>
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
            houseIds: [],
            devices: [], // 设备列表
            personnels: [],
            dialogFormVisible: false,
            disciplineVisible: false,
            updateFormVisible: false,
            deleteVisible: false,
            form: {
                id: '',
                description: '',
                houseId: '',
                deviceId: '',
                ownerId: '',
                personnelId: '',
            },
            table: {
                description: '',
                houseId: '',
                deviceName: '',
                name: '',
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
                houseId: '',
                serviceName: '',
                name: '',
                personnelName: '',
                status: '',
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
        this.fetchHouseIds();
        this.fetchPersonnels();
    },
    methods: {
        loadDevices() {
            // 根据选中的 houseId 获取设备数据
            if (this.form.houseId) {
                // 发起异步请求获取设备数据，假设有一个名为 getDevicesByHouseId 的接口
                // 你需要根据实际情况替换为实际的接口和参数
                axios.get(`http://localhost:8082/repair/getDevicesByHouseId?houseId=${this.form.houseId}`)
                    .then(response => {
                        this.devices = response.data.data;
                    })
                    .catch(error => {
                        console.error('获取设备数据失败:', error);
                    });
            } else {
                // 如果没有选择 houseId，清空设备列表
                this.devices = [];
            }
        },
        fetchPersonnels() {
            // 异步请求获取物业人员列表
            axios.get('http://localhost:8082/repair/selectPersonnel')
                .then(response => {
                    this.personnels = response.data.data;
                })
                .catch(error => {
                    console.error('获取物业人员列表失败:', error);
                });
        },
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
        updateRepair() {

            axios.post(`http://localhost:8082/repair/updateRepair`, this.form, {
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => {
                    // 处理成功更新
                    console.log('业务人员分配成功', response.data);
                    // 调用重新查询数据的方法
                    this.getAll();

                    // 显示更新成功的提示
                    this.$message.success('业务人员分配成功');
                })
                .catch(error => {
                    console.error('业务人员分配时发生错误:', error);
                    // 处理更新失败
                    // 处理更新失败的情况
                    // 显示更新失败的提示
                    this.$message.error('业务人员分配失败');
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
            axios.delete(`http://localhost:8082/repair/deleteRepairById/` + row.id)
                .then(response => {
                    // 处理删除成功的情况，可以更新前端UI，例如移除对应的行
                    // 实现真正的删除逻辑，从 tableData 数组中删除指定的行数据
                    const index = this.tableData.indexOf(row);
                    if (index !== -1) {
                        this.tableData.splice(index, 1);
                    }
                    this.getAll();
                    this.$message.success('删除成功');
                })
                .catch(error => {
                    console.error('删除学员时发生错误:', error);
                    // 处理删除失败的情况
                    this.$message.success('删除失败');
                });
        },
        fetchAllParking() {
            // 从服务器获取所有人员数据
            axios.get("http://localhost:8082/park/selectPark").then((res) => {
                this.allParking = res.data.data; // 假设响应包含人员数组
                console.log(this.allPersons);
            });
        },
        fetchAllPersons() {
            // 从服务器获取所有人员数据
            axios.get("http://localhost:8082/park/selectOwner").then((res) => {
                this.allPersons = res.data.data; // 假设响应包含人员数组
                console.log(this.allParking);
            });
        },
        fetchHouseIds() {
            axios.get("http://localhost:8082/repair/selectHouseIds").then((res) => {
                this.houseIds = res.data.data;
            });
        },
        addRepair() {
            axios.post('http://localhost:8082/repair/insertRepair', this.form, {
                headers: {
                    'Content-Type': 'application/json',
                },
            }).then(response => {
                // 检查响应是否包含错误状态码
                if (response.status == 204) {
                    throw new Error('请求失败');
                }
                // 处理添加成功的情况
                console.log('学员添加成功:', response.data);
                // 在前端更新UI，例如将新的学员数据加入到tableData数组
                this.tableData.push(response.data.data);
                this.$message.success('添加成功');
                this.getAll();
            }).catch(error => {
                console.error('添加维修时发生错误:', error);
                // 处理添加失败的情况
                this.$message.error('添加失败');
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

            if (this.pagination.houseId !== undefined && this.pagination.houseId !== null) {
                param += "?houseId=" + this.pagination.houseId;
            }

            if (this.pagination.serviceName !== undefined && this.pagination.serviceName !== null) {
                param += "&serviceName=" + this.pagination.serviceName;
            }

            if (this.pagination.name !== undefined && this.pagination.name !== null) {
                param += "&name=" + this.pagination.name;
            }

            if (this.pagination.personnelName !== undefined && this.pagination.personnelName !== null) {
                param += "&personnelName=" + this.pagination.personnelName;
            }

            if (this.pagination.status !== undefined && this.pagination.status !== null) {
                param += "&status=" + this.pagination.status;
            }

            if (this.pagination.date !== undefined && this.pagination.date !== null) {
                param += "&date1=" + this.pagination.date[0];
                param += "&date2=" + this.pagination.date[1];
            }

            // 发送异步请求
            axios.get("http://localhost:8082/repair/selectRepairs/" + this.pagination.currentPage + '/' + this.pagination.pageSize + param).then((res) => {
                this.pagination.pageSize = res.data.data.size;
                this.pagination.currentPage = res.data.data.current;
                this.pagination.total = res.data.data.total;
                // 处理状态列显示
                this.tableData = res.data.data.records.map(record => {
                    // 将状态值转换为对应的文本
                    record.status = record.status === '0' ? '待受理' : (record.status === '1' ? '已受理' : '修理完毕');
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

            axios.post('http://localhost:8082/repair/batchDeleteRepair', deleteIds)
                .then(response => {
                    // 处理批量删除成功的情况
                    console.log('批量删除成功:', response.data);

                    // 更新前端UI，从tableData数组中移除选定的行数据
                    this.tableData = this.tableData.filter(row => !selectedRows.includes(row));
                    this.getAll();
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