<template>
    <div Meter="Meter">
        <el-card shadow="always">
        <el-form style=" margin-left: 0;">
            <el-row>
            <el-form-item label="抄表人" style="margin-top: 20px; ">
                <el-input v-model="data.personnel" placeholder="请输入抄表人" style="width: 210px;" />
            </el-form-item>
            <el-button type="primary" style="margin-left: 30px; width: 100px;margin-top: 20px;" @click="getMyMeter">查询</el-button>
            </el-row>
        </el-form>
        <el-table :data="data.tableData" style="margin-top: 30px;width: 1000px;" stripe>
            <el-table-column prop="meterId" label="序号" width="120" />
            <el-table-column prop="meterType" label="仪表类别" width="120" />
            <el-table-column prop="meterValue" label="仪表类别" width="120" />
            <el-table-column prop="personnel" label="抄表人" width="120" />
            <el-table-column prop="updateTime" label="更新时间" width="120" />
            <el-table-column fixed="right" label="操作" width="200">
            <template #default="scope">
                <el-button link type="primary" @click="EditMeterFormLoad(scope.row)">
                编辑
                </el-button>
            </template>
            </el-table-column>
        </el-table>
        <el-pagination style="margin-top: 30px;" v-model:current-page="data.currentPage4" v-model:page-size="data.pageSize4"
            :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper" :total="data.totalItem"
            @size-change="getMyMeter()" @current-change="getMyMeter()" />
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
        </el-form>
        <template #footer>
        <span Meter="dialog-footer">
            <el-button @click="data.EditMeterDialogFormVisible = false">取消</el-button>
            <el-button type="primary" @click="EditMyMeter()">
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
    })
    
    const data = reactive({
    hostname: 'localhost',
    totalItem: 0,
    currentPage4: 1,
    pageSize4: 10,
    
    EditMeterDialogFormVisible: false,
    NewMeterDialogFormVisible: false,
    tableData: [],
    personnel:''
    })
    
    const EditMeterFormRef = ref();
    
    function EditMyMeter() {
        EditMeterFormRef.value.validate((valid) => {
            if (valid) {
            axios({
                method: 'post',
                url: 'http://' + data.hostname + ':8081/EditMyMeter',
                data: {
                meterId: EditMeterForm.meterId,
                meterType: EditMeterForm.meterType,
                meterValue: EditMeterForm.meterValue,
                }
            }).then(function (response) {
                if (response.data.code == 1) {
                ElMessage({
                    type: 'success',
                    message: '修改成功',
                })
                getMyMeter()
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
    }
    
    function getMyMeter() {
    axios({
        method: 'get',
        url: 'http://' + data.hostname + ':8081/getMyMeter',
        params: {
        page: data.currentPage4,
        pageSize: data.pageSize4,
        personnel: data.personnel,
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
    getMyMeter();

</script>
    