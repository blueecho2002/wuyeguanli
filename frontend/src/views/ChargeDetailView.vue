<template>
  <div class="class">
      <el-form style=" margin-left: 0;">
        <el-row>
          <el-form-item label="收费项目" prop="chargeItem" style="margin-top: 20px; padding-left: 0px; margin-left: 0px;">
            <el-select v-model="data.chargeItem" placeholder="请选择收费项目" clearable style="width: 140px;">
              <el-option v-for="item in data.options" :key="item.id" :label="item.name" :value="item.name" />

            </el-select>
          </el-form-item>
          <el-form-item label="付款人" style="margin-top: 20px; margin-left: 30px;">
            <el-input v-model="data.owner" placeholder="请输入付款人" style="width: 120px;" clearable />
          </el-form-item>
          <el-form-item label="付款人ID" style="margin-top: 20px; margin-left: 30px;">
            <el-input v-model="data.ownerId" placeholder="请输入付款人ID" style="width: 120px;" clearable />
          </el-form-item>
          <el-form-item label="缴费时间" style="margin-top: 20px; margin-left: 30px;">
            <el-date-picker v-model="data.date" type="datetimerange" start-placeholder="Start Date"
              end-placeholder="End Date" :default-time="defaultTime" value-format="YYYY-MM-DD HH:m:s" />
            <el-button type="primary" style="margin-left: 30px; width: 100px;" @click="getChargeDetail">查询</el-button>
            <el-button type="primary" style="margin-left: 10px; width: 100px;" @click="reset">重置</el-button>
          </el-form-item>

        </el-row>
        <el-button @click="data.newDetailDialogFormVisible = true" type="primary"
          style="margin-left: 50px; width: 100px; margin-left: 0;">新建收费明细</el-button>

        <el-button @click="deleteSelectedRows" type="danger"
          style="margin-left: 50px; width: 100px; margin-left: 10px;">批量删除</el-button>
      </el-form>
      <el-table :data="data.tableData" style="margin-top: 30px;width: 1430px;" stripe ref="tableRef">
        <el-table-column type="selection" width="50" />
        <el-table-column prop="id" label="明细ID" width="120" />
        <el-table-column prop="chargeItemName" label="收费项目" width="200" />
        <el-table-column prop="ownerId" label="付款人ID" width="120" />
        <el-table-column prop="owner" label="付款人" width="120" />
        <el-table-column prop="payMoney" label="应收金额（元）" width="130" />
        <el-table-column prop="actualMoney" label="实收金额（元）" width="130" />
        <el-table-column prop="payTime" label="缴费时间" width="180" />
        <el-table-column prop="updateTime" label="更新时间" width="180" />
        <el-table-column fixed="right" label="操作" width="200">
          <template #default="scope">
            <el-button link type="primary" @click="editDetailFormLoad(scope.row)">
              编辑
            </el-button>
            <el-button link type="danger" @click="deleteSingle(scope.row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination style="margin-top: 30px;" v-model:current-page="data.currentPage4" v-model:page-size="data.pageSize4"
        :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper" :total="data.totalItem"
        @size-change="getChargeDetail()" @current-change="getChargeDetail()" />
  </div>

  <!-- 修改明细对话框 -->
  <el-dialog v-model="data.editDetailDialogFormVisible" title="修改收费明细">
    <!-- {{ editDetailForm.chargeItemId }} -->
    <el-form :model="editDetailForm" :rules="rules" ref="editDetailFormRef">
      <el-form-item label="收费项目" :label-width="formLabelWidth" prop="chargeItem">
        <el-select v-model="editDetailForm.chargeItemId" placeholder="请选择收费项目" clearable>
          <el-option v-for="item in data.options" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="付款人ID" :label-width="formLabelWidth" prop="ownerId">
        <el-input v-model="editDetailForm.ownerId" autocomplete="off" placeholder="请输入付款人ID" />
      </el-form-item>
      <el-form-item label="应收金额" :label-width="formLabelWidth" prop="payMoney">
        <el-input v-model="editDetailForm.payMoney" autocomplete="off" placeholder="请输入应收金额" />
      </el-form-item>
      <el-form-item label="实收金额" :label-width="formLabelWidth" prop="actualMoney">
        <el-input v-model="editDetailForm.actualMoney" autocomplete="off" placeholder="请输入实收金额" />
      </el-form-item>

      <el-form-item label="缴费时间" :label-width="formLabelWidth" prop="payTime">
        <el-date-picker v-model="editDetailForm.payTime" type="datetime" placeholder="Pick a Date"
          format="YYYY/MM/DD HH:mm:ss" value-format="x" />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.editDetailDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="editDetail()">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 新增付费明细对话框 -->
  <el-dialog v-model="data.newDetailDialogFormVisible" title="新建收费明细">
    <el-form :model="newDetailForm" :rules="rules" ref="newDetailFormRef">
      <el-form-item label="收费项目" :label-width="formLabelWidth">
        <el-select v-model="newDetailForm.chargeItemId" placeholder="请选择收费项目" clearable prop="chargeItem">
          <el-option v-for="item in data.options" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="付款人ID" :label-width="formLabelWidth" prop="ownerId">
        <el-input v-model="newDetailForm.ownerId" autocomplete="off" placeholder="请输入付款人" />
      </el-form-item>
      <el-form-item label="应收金额" :label-width="formLabelWidth" prop="payMoney">
        <el-input v-model="newDetailForm.payMoney" autocomplete="off" placeholder="请输入应收金额" />
      </el-form-item>
      <el-form-item label="实收金额" :label-width="formLabelWidth" prop="actualMoney">
        <el-input v-model="newDetailForm.actualMoney" autocomplete="off" placeholder="请输入实收金额" />
      </el-form-item>

      <el-form-item label="缴费时间" :label-width="formLabelWidth" prop="payTime">
        <el-date-picker v-model="newDetailForm.payTime" type="datetime" placeholder="Pick a Date"
          format="YYYY/MM/DD HH:mm:ss" value-format="x" />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.newDetailDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addChargeDetail()">
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
  chargeItem: [
    //{ required: true, message: '请选择收费项目', trigger: 'change' },
  ],
  ownerId: [
    { required: true, message: '请输入付款人', trigger: 'blur' },
  ],
  payMoney: [
    { required: true, message: '请输入应收金额', trigger: 'blur' },
  ],
  actualMoney: [
    { required: true, message: '请输入实收金额', trigger: 'blur' },
  ],
  payTime: [
    { required: true, message: '请选择缴费时间', trigger: 'blur' },
  ],
})


const editDetailForm = reactive({
  id: '',
  chargeItemName: '',
  chargeItemId: '',
  ownerId: '',
  owner: '',
  payMoney: '',
  actualMoney: '',
  payTime: '',
  createTime: '',
  updateTime: '',
})

const newDetailForm = reactive({
  id: '',
  ownerId: '',
  chargeItemId: '',
  payMoney: '',
  actualMoney: '',
  payTime: '',
})


const data = reactive({
  hostname: 'localhost',
  totalItem: 0,
  currentPage4: 1,
  pageSize4: 10,

  date: '',//日期选择器日期
  owner: '',
  ownerId: '',
  chargeItem: '',

  editDetailDialogFormVisible: false,
  newDetailDialogFormVisible: false,

  tableData: [],
  classname: '',
  options: [],
})

const newDetailFormRef = ref();
const editDetailFormRef = ref();
const tableRef = ref();

function deleteSingle(id) {
  ElMessageBox.confirm(
    '您确定要删除该收费明细吗?',
    'Warning',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(() => {
    deleteChargeDetail(id)
  }).catch(() => { })
}

function deleteSelectedRows() {
  ElMessageBox.confirm(
    '您确定要删除该收费明细吗?',
    'Warning',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(() => {
    const selectedRows = tableRef.value.getSelectionRows()
    console.log(selectedRows);
    selectedRows.forEach(element => {
      deleteChargeDetail(element.id);
    });
  }).then(() => {
    deleteChargeDetail(id)
  }).catch(() => { })

}

function editDetailFormLoad(row) {
  console.log(row);
  data.editDetailDialogFormVisible = true
  editDetailForm.id = row.id;
  editDetailForm.chargeItemName = row.chargeItemName;
  editDetailForm.chargeItemId = row.chargeItemId;
  editDetailForm.owner = row.owner;
  editDetailForm.payMoney = row.payMoney;
  editDetailForm.actualMoney = row.actualMoney;
  editDetailForm.payTime = row.payTime;
  editDetailForm.ownerId = row.ownerId;
  editDetailForm.createTime = row.createTime;
  editDetailForm.updateTime = null;
}

function reset() {
  data.chargeItem = '';
  data.ownerId = '';
  data.owner = '';
  data.date = '';
  getAllChargeItem();
  getChargeDetail();
}

function addChargeDetail() {
  newDetailFormRef.value.validate((valid) => {
    if (valid) {
      axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/addChargeDetail',
        data: {
          chargeItemId: newDetailForm.chargeItemId,
          ownerId: newDetailForm.ownerId,
          payMoney: newDetailForm.payMoney,
          actualMoney: newDetailForm.actualMoney,
          payTime: newDetailForm.payTime,
        }
      }).then(function (response) {
        if (response.data.code == 1) {
          ElMessage({
            type: 'success',
            message: '添加成功',
          })
          getChargeDetail()
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
      data.newDetailDialogFormVisible = false
    } else {
      ElMessage({
        type: 'error',
        message: '添加失败',
      })
    }
  })


}

function editDetail() {
  editDetailFormRef.value.validate((valid) => {
    if (valid) {
      axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/editChargeDetail',
        data: {
          id: editDetailForm.id,
          owner: editDetailForm.owner,
          ownerId: editDetailForm.ownerId,
          payMoney: editDetailForm.payMoney,
          actualMoney: editDetailForm.actualMoney,
          createTime: editDetailForm.to_time,
          updateTime: editDetailForm.teacher,
          payTime: newDetailForm.payTime,
          chargeItemName: editDetailForm.chargeItemName,
          chargeItemId: editDetailForm.chargeItemId,
          payTime: editDetailForm.payTime,
        }
      }).then(function (response) {
        if (response.data.code == 1) {
          ElMessage({
            type: 'success',
            message: '修改成功',
          })
          getChargeDetail()
        } else {
          ElMessage({
            type: 'error',
            message: response.data.msg,
          })
        }
        console.log(response);
      }).catch(function (error) {
        console.log(error);
      })
      data.editDetailDialogFormVisible = false;
    } else {
      ElMessage({
        type: 'error',
        message: '修改失败',
      })
    }
  })


}

function deleteChargeDetail(id) {
  axios({
    method: 'delete',
    url: 'http://' + data.hostname + ':8081/deleteChargeDetail/' + id,
  }).then(function (response) {
    if (response.data.code == 1) {
      ElMessage({
        type: 'success',
        message: '删除成功',
      })
      getChargeDetail()
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
}

function getChargeDetail() {
  axios({
    method: 'get',
    url: 'http://localhost:8081/getChargeDetail',
    params: {
      page: data.currentPage4,
      pageSize: data.pageSize4,
      owner: data.owner,
      ownerId: data.ownerId,
      chargeItem: data.chargeItem,
      from_time: data.date == null ? null : data.date[0],
      to_time: data.date == null ? null : data.date[1],
    }
  }).then(function (response) {
    if (response.data.code == 1) {
      data.totalItem = response.data.data.total
      data.tableData = response.data.data.rows;
    } else {
      ElMessage({
        type: 'error',
        message: '登录过期，请重新登录',
      })
    }
  }).catch(function (error) {
    console.log(localStorage.getItem('token'));
    console.log(error);
  });
}

function getAllChargeItem() {
  axios({
    method: 'get',
    url: 'http://localhost:8081/getAllChargeItem',
  }).then(function (response) {
    data.options = response.data.data;
  }).catch(function (error) {
    console.log(localStorage.getItem('token'));
    console.log(error);
  });
}

getChargeDetail();
getAllChargeItem();

const defaultTime = [
  new Date(2000, 1, 1, 0, 0, 0),
  new Date(2000, 2, 1, 0, 0, 0),
]

</script>
