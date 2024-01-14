<template>
<div owner="owner">
    <el-card shadow="always">
    <el-form style=" margin-left: 0;">
        <el-row>
        <el-form-item label="投诉人名称" style="margin-top: 20px; ">
            <el-input v-model="data.name" placeholder="请输入投诉人名称" style="width: 210px;" />
        </el-form-item>
        <el-button type="primary" style="margin-left: 30px; width: 100px;margin-top: 20px;" @click="getComplain">查询</el-button>
        </el-row>
        <el-row>
        <el-button @click="data.NewComplainDialogFormVisible = true" type="primary"
        style="margin-left: 50px; width: 100px; margin-left: 0;">新增投诉</el-button>
        </el-row>
    </el-form>
    <el-table :data="data.tableData" style="margin-top: 30px;" stripe border>
        <el-table-column prop="id" label="序号" width="80" />
        <el-table-column prop="description" label="投诉描述" width="120" />
        <el-table-column prop="name" label="投诉人姓名" width="200" />
        <el-table-column prop="gender" label="投诉人性别" width="130" :formatter="sexChange"/>
        <el-table-column prop="phoneNumber" label="投诉人电话" width="120" />
        <el-table-column prop="personnelName" label="物业姓名" width="120" />
        <el-table-column prop="personnelPhoneNumber" label="物业电话" width="120" />
        <el-table-column prop="status" label="状态" width="120" :formatter="statusChange"/>
        <el-table-column prop="updateTime" label="更新时间" width="120" />
        <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
            <el-button link type="primary" @click="EditComplainFormLoad(scope.row)">
            编辑
            </el-button>
            <el-button link type="primary" @click="DeleteComplain(scope.row.id)">
            删除
            </el-button>
        </template>
        </el-table-column>
    </el-table>
    <el-pagination style="margin-top: 30px;" v-model:current-page="data.currentPage4" v-model:page-size="data.pageSize4"
        :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper" :total="data.totalItem"
        @size-change="getComplain()" @current-change="getComplain()" />
    </el-card>
</div>

<!-- 修改投诉对话框 -->
<el-dialog v-model="data.EditComplainDialogFormVisible" title="修改投诉">
    <el-form :model="EditComplainForm" :rules="rules" ref="EditComplainFormRef">
    <el-form-item label="投诉描述" :label-width="formLabelWidth" prop="description">
        <el-input v-model="EditComplainForm.description" autocomplete="off" placeholder="请输入投诉描述" />
    </el-form-item>
    </el-form>
    <template #footer>
    <span owner="dialog-footer">
        <el-button @click="data.EditComplainDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="EditComplain()">
        确认
        </el-button>
    </span>
    </template>
</el-dialog>

<!-- 新增投诉对话框 -->
<el-dialog v-model="data.NewComplainDialogFormVisible" title="新增投诉">
    <el-form :model="NewComplainForm" :rules="rules" ref="NewComplainFormRef">
    <el-form-item label="投诉描述" :label-width="formLabelWidth" prop="description">
        <el-input v-model="NewComplainForm.description" autocomplete="off" placeholder="请输入投诉描述" />
    </el-form-item>
    <el-form-item label="投诉人姓名" :label-width="formLabelWidth" prop="name">
        <el-input v-model="NewComplainForm.name" autocomplete="off" placeholder="请输入投诉人姓名" />
    </el-form-item>
    <el-form-item label="投诉人性别" :label-width="formLabelWidth" prop="gender">
        <el-input v-model="NewComplainForm.gender" autocomplete="off" placeholder="请输入投诉人性别" />
    </el-form-item>
    <el-form-item label="投诉人电话" :label-width="formLabelWidth" prop="phoneNumber">
        <el-input v-model="NewComplainForm.phoneNumber" autocomplete="off" placeholder="请输入投诉人电话" />
    </el-form-item>
    </el-form>
    <template #footer>
    <span owner="dialog-footer">
        <el-button @click="data.NewComplainDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="AddComplain()">
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
    { required: true, message: '请输入投诉人名称', trigger: 'change' },
    { min: 2, max: 30, message: '投诉人名称非法', trigger: 'change' },
],
})


const EditComplainForm = reactive({
id:'',
description:'',
})

const NewComplainForm = reactive({
description:'',
name: '',
gender:'',
phoneNumber:'',
})

const data = reactive({
hostname: 'localhost',
totalItem: 0,
currentPage4: 1,
pageSize4: 10,

EditComplainDialogFormVisible: false,
NewComplainDialogFormVisible: false,
tableData: [],
name: '',
})

const NewComplainFormRef = ref();
const EditComplainFormRef = ref();

function AddComplain() {

NewComplainFormRef.value.validate((valid) => {
    if (valid) {
    axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/AddComplain',
        data: {
        description: NewComplainForm.description,   
        name: NewComplainForm.name,
        gender: NewComplainForm.gender,
        phoneNumber: NewComplainForm.phoneNumber,
        }
    }).then(function (response) {
        if (response.data.code == 1) {
        ElMessage({
            type: 'success',
            message: '添加成功',
        })
        getComplain()
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
    data.NewComplainDialogFormVisible = false
    } else {
    ElMessage({
        type: 'error',
        message: '添加失败',
    })
    }
})


}

function EditComplain() {

EditComplainFormRef.value.validate((valid) => {
    if (valid) {
    axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/EditComplain',
        data: {
        id:EditComplainForm.id,
        description:EditComplainForm.description,   
        }
    }).then(function (response) {
        if (response.data.code == 1) {
        ElMessage({
            type: 'success',
            message: '修改成功',
        })
        getComplain()
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
    data.EditComplainDialogFormVisible = false;
    } else {
    ElMessage({
        type: 'error',
        message: '修改失败',
    })
    }
})


}

function EditComplainFormLoad(row) {
data.EditComplainDialogFormVisible = true
EditComplainForm.id = row.id;
EditComplainForm.description = row.description;
}

function DeleteComplain(id) {
//console.log('http://'+data.hostname+':8081/DeleteComplain/' + id)
ElMessageBox.confirm(
    '您确定要删除该投诉吗?',
    'Warning',
    {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    }
).then(() => {
    axios({
    method: 'delete',
    url: 'http://' + data.hostname + ':8081/DeleteComplain/' + id,
    }).then(function (response) {
    if (response.data.code == 1) {
        ElMessage({
        type: 'success',
        message: '删除成功',
        })
        getComplain()
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
})
    .catch(() => {
    ElMessage({
        type: 'info',
        message: '删除取消',
    })
    })
}

function getComplain() {
axios({
    method: 'get',
    url: 'http://' + data.hostname + ':8081/getComplain',
    params: {
    page: data.currentPage4,
    pageSize: data.pageSize4,
    name: data.name,
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

function sexChange(tableData){
    if (tableData.sex === 'M'||'0') {
        return "男"
    }else if(tableData.sex === 'F'||'1'){
        return '女'
    }
}

function statusChange(tableData){
    if (tableData.status === '0') {
        return "未受理"
    }else if(tableData.status === '1'){
        return '已受理'
    }else if(tableData.status === '2'){
        return '已处理完毕'
    }
}
getComplain();

</script>
