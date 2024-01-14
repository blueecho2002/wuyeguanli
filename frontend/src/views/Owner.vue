<template>
<div owner="owner">
    <el-card shadow="always">
    <el-form style=" margin-left: 0;">
        <el-row>
        <el-form-item label="住户名称" style="margin-top: 20px; ">
            <el-input v-model="data.OwnerName" placeholder="请输入住户名称" style="width: 210px;" />
        </el-form-item>
        <el-button type="primary" style="margin-left: 30px; width: 100px;margin-top: 20px;" @click="getOwner">查询</el-button>
        </el-row>
        <el-row>
        <el-button @click="data.NewOwnerDialogFormVisible = true" type="primary"
        style="margin-left: 50px; width: 100px; margin-left: 0;">新增住户</el-button>
        <el-button type="primary" style="margin-left: 30px; width: 100px;" @click="group">按房间分组</el-button>
        </el-row>
    </el-form>
    <el-table :data="data.tableData" style="margin-top: 30px;" stripe border>
        <el-table-column prop="id" label="序号" width="80" />
        <el-table-column prop="name" label="住户名称" width="120" />
        <el-table-column prop="idCard" label="身份证" width="200" />
        <el-table-column prop="telephone" label="住户电话" width="130" />
        <el-table-column prop="profession" label="住户职业" width="120" />
        <el-table-column prop="sex" label="住户性别" width="120" :formatter="sexChange">
        </el-table-column>
        <el-table-column prop="type" label="住户类别" width="120" :formatter="typeChange"/>
        <el-table-column prop="remark" label="备注" width="120" />
        <el-table-column prop="birthday" label="出生日期" width="120" />
        <el-table-column prop="room" label="住户房间" width="120" />
        <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
            <el-button link type="primary" @click="EditOwnerFormLoad(scope.row)">
            编辑
            </el-button>
            <el-button link type="primary" @click="DeleteOwner(scope.row.id)">
            删除
            </el-button>
        </template>
        </el-table-column>
    </el-table>
    <el-pagination style="margin-top: 30px;" v-model:current-page="data.currentPage4" v-model:page-size="data.pageSize4"
        :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper" :total="data.totalItem"
        @size-change="fenYe()" @current-change="fenYe()" />
    </el-card>
</div>

<!-- 修改住户对话框 -->
<el-dialog v-model="data.EditOwnerDialogFormVisible" title="修改住户">
    <el-form :model="EditOwnerForm" :rules="rules" ref="EditOwnerFormRef">
    <el-form-item label="住户名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="EditOwnerForm.name" autocomplete="off" placeholder="请输入住户名称" />
    </el-form-item>
    <el-form-item label="身份证" :label-width="formLabelWidth" prop="idCard">
        <el-input v-model="EditOwnerForm.idCard" autocomplete="off" placeholder="请输入身份证" />
    </el-form-item>
    <el-form-item label="住户电话" :label-width="formLabelWidth" prop="telephone">
        <el-input v-model="EditOwnerForm.telephone" autocomplete="off" placeholder="请输入住户电话" />
    </el-form-item>
    <el-form-item label="住户职业" :label-width="formLabelWidth" prop="profession">
        <el-input v-model="EditOwnerForm.profession" autocomplete="off" placeholder="请输入住户职业" />
    </el-form-item>
    <el-form-item label="住户性别" :label-width="formLabelWidth" prop="sex">
        <el-input v-model="EditOwnerForm.sex" autocomplete="off" placeholder="请输入住户性别" />
    </el-form-item>
    <el-form-item label="住户类别" :label-width="formLabelWidth" prop="type">
        <el-input v-model="EditOwnerForm.type" autocomplete="off" placeholder="请输入住户类别" />
    </el-form-item>
    <el-form-item label="备注" :label-width="formLabelWidth" prop="remark">
        <el-input v-model="EditOwnerForm.remark" autocomplete="off" placeholder="请输入住户备注" />
    </el-form-item>
    <el-form-item label="住户出生日期" :label-width="formLabelWidth" prop="birthday">
        <el-input v-model="EditOwnerForm.birthday" autocomplete="off" placeholder="请输入住户出生日期" />
    </el-form-item>
    <el-form-item label="住户房间" :label-width="formLabelWidth" prop="room">
        <el-input v-model="EditOwnerForm.room" autocomplete="off" placeholder="请输入住户房间" />
    </el-form-item>
    </el-form>
    <template #footer>
    <span owner="dialog-footer">
        <el-button @click="data.EditOwnerDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="EditOwner()">
        确认
        </el-button>
    </span>
    </template>
</el-dialog>

<!-- 新增住户对话框 -->
<el-dialog v-model="data.NewOwnerDialogFormVisible" title="新增住户">
    <el-form :model="NewOwnerForm" :rules="rules" ref="NewOwnerFormRef">
    <el-form-item label="住户名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="NewOwnerForm.name" autocomplete="off" placeholder="请输入住户名称" />
    </el-form-item>
    <el-form-item label="身份证" :label-width="formLabelWidth" prop="idCard">
        <el-input v-model="NewOwnerForm.idCard" autocomplete="off" placeholder="请输入身份证" />
    </el-form-item>
    <el-form-item label="住户电话" :label-width="formLabelWidth" prop="telephone">
        <el-input v-model="NewOwnerForm.telephone" autocomplete="off" placeholder="请输入住户电话" />
    </el-form-item>
    <el-form-item label="住户职业" :label-width="formLabelWidth" prop="profession">
        <el-input v-model="NewOwnerForm.profession" autocomplete="off" placeholder="请输入住户职业" />
    </el-form-item>
    <el-form-item label="住户性别" :label-width="formLabelWidth" prop="sex">
        <el-input v-model="NewOwnerForm.sex" autocomplete="off" placeholder="请输入住户性别" />
    </el-form-item>
    <el-form-item label="住户类别" :label-width="formLabelWidth" prop="type">
        <el-input v-model="NewOwnerForm.type" autocomplete="off" placeholder="请输入住户类别" />
    </el-form-item>
    <el-form-item label="备注" :label-width="formLabelWidth" prop="remark">
        <el-input v-model="NewOwnerForm.remark" autocomplete="off" placeholder="请输入住户备注" />
    </el-form-item>
    <el-form-item label="住户出生日期" :label-width="formLabelWidth" prop="birthday">
        <el-input v-model="NewOwnerForm.birthday" autocomplete="off" placeholder="请输入住户出生日期" />
    </el-form-item>
    <el-form-item label="住户房间" :label-width="formLabelWidth" prop="room">
        <el-input v-model="NewOwnerForm.room" autocomplete="off" placeholder="请输入住户房间" />
    </el-form-item>
    </el-form>
    <template #footer>
    <span owner="dialog-footer">
        <el-button @click="data.NewOwnerDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="AddOwner()">
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
    { required: true, message: '请输入住户名称', trigger: 'change' },
    { min: 2, max: 30, message: '住户名称非法', trigger: 'change' },
],
room: [

],

})


const EditOwnerForm = reactive({
id: '',
name: '',
idCard:'',
telephone:'',
profession:'',
sex:'',
type:'',
remark:'',
birthday:'',
room: '',
})

const NewOwnerForm = reactive({
id: '',
name: '',
idCard:'',
telephone:'',
profession:'',
sex:'',
type:'',
remark:'',
birthday:'',
room: '',
})

const data = reactive({
hostname: 'localhost',
totalItem: 0,
currentPage4: 1,
pageSize4: 10,

EditOwnerDialogFormVisible: false,
NewOwnerDialogFormVisible: false,
tableData: [],
OwnerName: '',
isGroup:false
})

const NewOwnerFormRef = ref();
const EditOwnerFormRef = ref();

function AddOwner() {

NewOwnerFormRef.value.validate((valid) => {
    if (valid) {
    axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/AddOwner',
        data: {
        name: NewOwnerForm.name,
        idCard: NewOwnerForm.idCard,
        telephone: NewOwnerForm.telephone,
        profession: NewOwnerForm.profession,
        sex: NewOwnerForm.sex,
        type: NewOwnerForm.type,
        remark: NewOwnerForm.remark,
        birthday: NewOwnerForm.birthday,
        room: NewOwnerForm.room,
        }
    }).then(function (response) {
        if (response.data.code == 1) {
        ElMessage({
            type: 'success',
            message: '添加成功',
        })
        getOwner()
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
    data.NewOwnerDialogFormVisible = false
    } else {
    ElMessage({
        type: 'error',
        message: '添加失败',
    })
    }
})


}

function EditOwner() {

EditOwnerFormRef.value.validate((valid) => {
    if (valid) {
    axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/EditOwner',
        data: {
        id: EditOwnerForm.id,
        name: EditOwnerForm.name,
        idCard: EditOwnerForm.idCard,
        telephone: EditOwnerForm.telephone,
        profession: EditOwnerForm.profession,
        sex: EditOwnerForm.sex,
        type: EditOwnerForm.type,
        remark: EditOwnerForm.remark,
        birthday: EditOwnerForm.birthday,
        room: EditOwnerForm.room,
        }
    }).then(function (response) {
        if (response.data.code == 1) {
        ElMessage({
            type: 'success',
            message: '修改成功',
        })
        getOwner()
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
    data.EditOwnerDialogFormVisible = false;
    } else {
    ElMessage({
        type: 'error',
        message: '修改失败',
    })
    }
})


}

function EditOwnerFormLoad(row) {
data.EditOwnerDialogFormVisible = true
EditOwnerForm.id = row.id;
EditOwnerForm.name = row.name;
EditOwnerForm.idCard = row.idCard;
EditOwnerForm.telephone = row.telephone;
EditOwnerForm.profession = row.profession;
EditOwnerForm.sex = row.sex;
EditOwnerForm.type = row.type;
EditOwnerForm.remark = row.remark;
EditOwnerForm.birthday = row.birthday;
EditOwnerForm.room = row.room;
}

function DeleteOwner(id) {
//console.log('http://'+data.hostname+':8081/DeleteOwner/' + id)
ElMessageBox.confirm(
    '您确定要删除该住户吗?',
    'Warning',
    {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    }
).then(() => {
    axios({
    method: 'delete',
    url: 'http://' + data.hostname + ':8081/DeleteOwner/' + id,
    }).then(function (response) {
    if (response.data.code == 1) {
        ElMessage({
        type: 'success',
        message: '删除成功',
        })
        getOwner()
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

function getOwner() {
    data.isGroup = false;
axios({
    method: 'get',
    url: 'http://' + data.hostname + ':8081/getOwner',
    params: {
    page: data.currentPage4,
    pageSize: data.pageSize4,
    name: data.OwnerName,
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

function typeChange(tableData){
    if (tableData.type === '0') {
        return "房主"
    }else if(tableData.type === '1'){
        return '租客'
    }
}

function group() {
    data.isGroup = true;
axios({
    method: 'get',
    url: 'http://' + data.hostname + ':8081/group',
    params: {
    page: data.currentPage4,
    pageSize: data.pageSize4,
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

function fenYe() {
    if (data.isGroup===false) {
        return getOwner();
    }else{
        return group();
    }
}
getOwner();

</script>
