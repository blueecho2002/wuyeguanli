<template>
    <div class="class">
    <el-card shadow="always">
        
        <el-table :data="data.tableData" style="margin-top: 30px;width: 1000px;" stripe>
        <el-table-column prop="name" label="小区名称" width="200" />
        <el-table-column prop="location" label="小区地址" width="200" />
        <el-table-column prop="scale" label="小区面积" width="120" />
        <el-table-column fixed="right" label="操作" width="200">
            <template #default="scope">
            <el-button link type="primary" @click="editInfoFormLoad(scope.row)">
                编辑
            </el-button>
            </template>
        </el-table-column>
        </el-table>
        
    </el-card>
    </div>

    <!-- 修改小区对话框 -->
    <el-dialog v-model="data.editInfoDialogFormVisible" title="修改小区">
    <el-form :model="editInfoForm" :rules="rules" ref="editInfoFormRef">
        <el-form-item label="小区名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="editInfoForm.name" autocomplete="off" placeholder="请输入小区名称" />
        </el-form-item>
        <el-form-item label="小区地址" :label-width="formLabelWidth" prop="location">
        <el-input v-model="editInfoForm.location" autocomplete="off" placeholder="请输入小区地址" />
        </el-form-item>
        <el-form-item label="小区面积" :label-width="formLabelWidth" prop="scale">
        <el-input v-model="editInfoForm.scale" autocomplete="off" placeholder="请输入小区面积" />
        </el-form-item>

    </el-form>
    <template #footer>
        <span class="dialog-footer">
        <el-button @click="data.editInfoDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="editInfo()">
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
    name: [
    { required: true, message: '请输入小区名称', trigger: 'change' },
    { min: 2, max: 30, message: '小区名称非法', trigger: 'change' },
    ],
    location: [

    ],
    scale: [
    { required: true, message: '请输入小区名称', trigger: 'change' },
    ],

})


const editInfoForm = reactive({
    name: '',
    location: '',
    scale: '',
})


const data = reactive({
    hostname: 'localhost',
    totalItem: 0,
    currentPage4: 1,
    pageSize4: 10,

    editInfoDialogFormVisible: false,
    newInfoDialogFormVisible: false,
    tableData: [],
    InfoName: '',
})

const editInfoFormRef = ref();

function editInfo() {

    editInfoFormRef.value.validate((valid) => {
    if (valid) {
        axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/editInfo',
        data: {
            name: editInfoForm.name,
            location: editInfoForm.location,
            scale: editInfoForm.scale,
        }
        }).then(function (response) {
        if (response.data.code == 1) {
            ElMessage({
            type: 'success',
            message: '修改成功',
            })
            getInfo()
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
        data.editInfoDialogFormVisible = false;
    } else {
        ElMessage({
        type: 'error',
        message: '修改失败',
        })
    }
    })


}

function editInfoFormLoad(row) {
    data.editInfoDialogFormVisible = true
    editInfoForm.name = row.name;
    editInfoForm.location = row.location;
    editInfoForm.scale = row.scale;
}


function getInfo() {
    axios({
    method: 'get',
    url: 'http://' + data.hostname + ':8081/getInfo',
    params: {
        page: data.currentPage4,
        pageSize: data.pageSize4,
        name: data.InfoName,
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

getInfo();

</script>
