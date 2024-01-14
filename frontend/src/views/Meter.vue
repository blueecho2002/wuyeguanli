<template>
    <div Meter="Meter">
        <el-card shadow="always">
        <el-form style=" margin-left: 0;">
            <el-row>
            <el-form-item label="仪表类别" style="margin-top: 20px; ">
                <el-input v-model="data.meterType" placeholder="请输入仪表类别" style="width: 210px;" />
            </el-form-item>
            <el-button type="primary" style="margin-left: 30px; width: 100px;margin-top: 20px;" @click="getAllMeter">查询</el-button>
            </el-row>
            <el-row>
            <el-button @click="data.NewMeterDialogFormVisible = true" type="primary"
            style="margin-left: 50px; width: 100px; margin-left: 0;">新增仪表</el-button>
            </el-row>
        </el-form>
        <el-table :data="data.tableData" style="margin-top: 30px;width: 1000px;" stripe>
            <el-table-column prop="meterId" label="序号" width="120" />
            <el-table-column prop="meterType" label="仪表类别" width="120" />
            <el-table-column prop="meterValue" label="仪表示数" width="120" />
            <el-table-column prop="personnel" label="抄表人" width="120" />
            <el-table-column prop="updateTime" label="更新时间" width="120" />
            <el-table-column fixed="right" label="操作" width="200">
            <template #default="scope">
                <el-button link type="primary" @click="EditMeterFormLoad(scope.row)">
                编辑
                </el-button>
                <el-button link type="primary" @click="DeleteMeter(scope.row.meterId)">
                删除
                </el-button>
            </template>
            </el-table-column>
        </el-table>
        <el-pagination style="margin-top: 30px;" v-model:current-page="data.currentPage4" v-model:page-size="data.pageSize4"
            :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper" :total="data.totalItem"
            @size-change="getAllMeter()" @current-change="getAllMeter()" />
        </el-card>
    </div>
    
    <!-- 修改仪表对话框 -->
    <el-dialog v-model="data.EditMeterDialogFormVisible" title="修改仪表">
        <el-form :model="EditMeterForm" :rules="rules" ref="EditMeterFormRef">
        <el-form-item label="仪表类别" :label-width="formLabelWidth" prop="meterType">
            <el-input v-model="EditMeterForm.meterType" autocomplete="off" placeholder="请输入仪表类别" />
        </el-form-item>
        <el-form-item label="仪表示数" :label-width="formLabelWidth" prop="meterValue">
            <el-input v-model="EditMeterForm.meterValue" autocomplete="off" placeholder="请输入仪表示数" />
        </el-form-item>
        <el-form-item label="抄表人" :label-width="formLabelWidth" prop="personnel">
            <el-input v-model="EditMeterForm.personnel" autocomplete="off" placeholder="请输入超表人" />
        </el-form-item>
        </el-form>
        <template #footer>
        <span Meter="dialog-footer">
            <el-button @click="data.EditMeterDialogFormVisible = false">取消</el-button>
            <el-button type="primary" @click="EditMeter()">
            确认
            </el-button>
        </span>
        </template>
    </el-dialog>
    
    <!-- 新增仪表对话框 -->
    <el-dialog v-model="data.NewMeterDialogFormVisible" title="新增仪表">
        <el-form :model="NewMeterForm" :rules="rules" ref="NewMeterFormRef">
        <el-form-item label="仪表类别" :label-width="formLabelWidth" prop="meterType">
            <el-input v-model="NewMeterForm.meterType" autocomplete="off" placeholder="请输入仪表类别" />
        </el-form-item>
        <el-form-item label="仪表示数" :label-width="formLabelWidth" prop="meterValue">
            <el-input v-model="NewMeterForm.meterValue" autocomplete="off" placeholder="请输入仪表示数" />
        </el-form-item>
        </el-form>
        <template #footer>
            <span Meter="dialog-footer">
                <el-button @click="data.NewMeterDialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="AddMeter()">
                确认
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>
    
<script setup>
    import { reactive, ref } from 'vue';
    import { ElMessageBox } from 'element-plus'
    import { inject } from 'vue';
    import { ElMessage } from 'element-plus';
    const axios = inject('$axios')
    
    const formLabelWidth = '140px'
    
    const rules = reactive({
    meterType: [
        { required: true, message: '请输入仪表类别', trigger: 'change' },
        { min: 2, max: 30, message: '仪表类别非法', trigger: 'change' },
    ],
    })
    
    
    const EditMeterForm = reactive({
    meterId: '',
    meterType:'',
    meterValue:'',
    personnel:''
    })
    
    const NewMeterForm = reactive({
    meterId: '',
    meterType:'',
    meterValue:'',
    })
    
    const data = reactive({
    hostname: 'localhost',
    totalItem: 0,
    currentPage4: 1,
    pageSize4: 10,
    
    EditMeterDialogFormVisible: false,
    NewMeterDialogFormVisible: false,
    tableData: [],
    meterType:''
    })
    
    const NewMeterFormRef = ref();
    const EditMeterFormRef = ref();
    
    function AddMeter() {
        NewMeterFormRef.value.validate((valid) => {
            if (valid) {
            axios({
                method: 'post',
                url: 'http://' + data.hostname + ':8081/AddMeter',
                data: {
                meterType: NewMeterForm.meterType,
                meterValue: NewMeterForm.meterValue,
                }
            }).then(function (response) {
                if (response.data.code == 1) {
                ElMessage({
                    type: 'success',
                    message: '添加成功',
                })
                getAllMeter()
                } else {
                ElMessage({
                    type: 'error',
                    message: '添加失败',
                })
                }
                console.log(response);
            }).catch(function (error) {
                console.log(error);
            })
            data.NewMeterDialogFormVisible = false
            } else {
            ElMessage({
                type: 'error',
                message: '添加失败',
            })
            }
        })
    }
    
    function EditMeter() {
        EditMeterFormRef.value.validate((valid) => {
            if (valid) {
            axios({
                method: 'post',
                url: 'http://' + data.hostname + ':8081/EditMeter',
                data: {
                meterId: EditMeterForm.meterId,
                meterType: EditMeterForm.meterType,
                meterValue: EditMeterForm.meterValue,
                personnel:EditMeterForm.personnel
                }
            }).then(function (response) {
                if (response.data.code == 1) {
                ElMessage({
                    type: 'success',
                    message: '修改成功',
                })
                getAllMeter()
                } else {
                ElMessage({
                    type: 'error',
                    message: '修改失败',
                })
                }
                console.log(response);
            }).catch(function (error) {
                console.log(error);
            })
            data.EditMeterDialogFormVisible = false;
            } else {
                ElMessage({
                    type: 'error',
                    message: '修改失败',
                })
            }
        })
    }
    
    function EditMeterFormLoad(row) {
        data.EditMeterDialogFormVisible = true
        EditMeterForm.meterId = row.meterId;
        EditMeterForm.meterType = row.meterType;
        EditMeterForm.meterValue = row.meterValue;
        EditMeterForm.personnel = row.personnel;
    }
    
    function DeleteMeter(meterId) {
        //console.log('http://'+data.hostname+':8081/DeleteMeter/' + meterId)
        ElMessageBox.confirm(
            '您确定要删除该仪表吗?',
            'Warning',
            {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            }
        ).then(() => {
            axios({
            method: 'delete',
            url: 'http://' + data.hostname + ':8081/DeleteMeter/' + meterId,
            }).then(function (response) {
            if (response.data.code == 1) {
                ElMessage({
                type: 'success',
                message: '删除成功',
                })
                getAllMeter()
            } else {
                ElMessage({
                type: 'error',
                message: '删除失败',
                })
            }
            console.log(response);
            }).catch(function (error) {
            console.log(error);
            });
        }).catch(() => {
            ElMessage({
                type: 'info',
                message: '删除取消',
            })
        })
    }
    
    function getAllMeter() {
    axios({
        method: 'get',
        url: 'http://' + data.hostname + ':8081/getAllMeter',
        params: {
        page: data.currentPage4,
        pageSize: data.pageSize4,
        meterType: data.meterType,
        }
    }).then(function (response) {
        if (response.data.code == 1) {
        ElMessage({
            type: 'success',
            message: '数据加载成功',
        });
        data.totalItem = response.data.data.total
        data.tableData = response.data.data.rows;
        } else {
        ElMessage({
            type: 'error',
            message: '加载失败',
        })
        }
    }).catch(function (error) {
        console.log(error);
    });
    }
    getAllMeter();

</script>
    