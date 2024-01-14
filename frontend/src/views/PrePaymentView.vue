<template>
  <div class="class">
      <el-form style=" margin-left: 0;">
        <el-row>
          <el-form-item label="住户姓名" style="margin-top: 20px;">
            <el-input v-model="data.owner" placeholder="请输入住户姓名" style="width: 120px;" clearable />
          </el-form-item>
          <el-form-item label="住户ID" style="margin-top: 20px; margin-left: 30px;">
            <el-input v-model="data.ownerId" placeholder="请输入住户ID" style="width: 120px;" clearable />
          </el-form-item>
          <el-form-item label="更新时间" style="margin-top: 20px; margin-left: 30px;">
            <el-date-picker v-model="data.date" type="datetimerange" start-placeholder="Start Date"
              end-placeholder="End Date" :default-time="defaultTime" value-format="YYYY-MM-DD HH:m:s" />
            <el-button type="primary" style="margin-left: 30px; width: 100px;" @click="getPrePayment()">查询</el-button>
            <el-button type="primary" style="margin-left: 10px; width: 100px;" @click="reset()">重置</el-button>
          </el-form-item>

        </el-row>
        <!-- <el-button @click="data.newDetailDialogFormVisible = true" type="primary"
          style="margin-left: 50px; width: 100px; margin-left: 0;">新建账号</el-button> -->

        <el-button @click="deleteSelectedRows" type="danger"
          style="margin-left: 50px; width: 100px; margin-left: 10px;">批量删除</el-button>
      </el-form>
      <el-table :data="data.tableData" style="margin-top: 30px;width: 1410px;" stripe ref="tableRef">
        <el-table-column type="selection" width="50" />
        <el-table-column prop="id" label="住户ID" width="150" />
        <el-table-column prop="owner" label="住户姓名" width="150" />
        <el-table-column prop="telephone" label="住户联系方式" width="200" />
        <el-table-column prop="balance" label="余额（元）" width="160" />
        <el-table-column prop="updateTime" label="更新时间" width="180" />
        <el-table-column fixed="right" label="操作" width="200">
          <template #default="scope">
            <el-button link type="primary" @click="editPrePaymentLoad(scope.row)">
              编辑
            </el-button>
            <el-button link type="danger" @click="deleteSingle(scope.row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination style="margin-top: 30px;" v-model:current-page="data.currentPage" v-model:page-size="data.pageSize"
        :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper" :total="data.totalItem"
        @size-change="getPrePayment()" @current-change="getPrePayment()" />
  </div>

  <!-- 修改账户余额对话框 -->
  <el-dialog v-model="data.editDetailDialogFormVisible" title="修改账户余额">
    <el-form :model="editPrePayment" :rules="rules" ref="editPrePaymentRef">
      <el-form-item label="住户ID" :label-width="formLabelWidth" prop="ownerId"  >
        <el-input v-model="editPrePayment.ownerId" autocomplete="off" placeholder="请输入付款人ID" disabled />
      </el-form-item>
      <el-form-item label="住户姓名" :label-width="formLabelWidth" prop="owner"  >
        <el-input v-model="editPrePayment.owner" autocomplete="off" placeholder="请输入应收金额"  disabled />
      </el-form-item>
      <el-form-item label="住户联系方式" :label-width="formLabelWidth" prop="telephone"  >
        <el-input v-model="editPrePayment.telephone" autocomplete="off" placeholder="请输入实收金额" disabled />
      </el-form-item>
      <el-form-item label="账户余额（元）" :label-width="formLabelWidth" prop="balance">
        <el-input v-model="editPrePayment.balance" autocomplete="off" placeholder="请输入实收金额" />
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


const editPrePayment = reactive({
  ownerName: '',
  money: 0,
  telephone:'',
  balance:0,
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
  currentPage: 1,
  pageSize: 10,

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
const editPrePaymentRef = ref();
const tableRef = ref();

function deleteSingle(id){
  ElMessageBox.confirm(
    '您确定要删除吗?',
    'Warning',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(() => {
    deletePrePayment(id)
  }).catch(()=>{})
}

function deleteSelectedRows() {
  ElMessageBox.confirm(
    '您确定要删除吗?',
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
      deletePrePayment(element.id);
    });
  }).then(() => {
    deletePrePayment(id)
  }).catch(()=>{})

}

function editPrePaymentLoad(row) {
  console.log(row);
  data.editDetailDialogFormVisible = true
  editPrePayment.owner = row.owner;
  editPrePayment.ownerId = row.id;
  editPrePayment.balance = row.balance;
  editPrePayment.telephone = row.telephone;
}

function reset() {
  data.chargeItem = '';
  data.ownerId = '';
  data.owner = '';
  data.date = '';
  getPrePayment();
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
  editPrePaymentRef.value.validate((valid) => {
    if (valid) {
      axios({
        method: 'get',
        url: 'http://' + data.hostname + ':8081/editBalance',
        params: {
          ownerId: editPrePayment.ownerId,
          balance: editPrePayment.balance,
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

function deletePrePayment(id) {
    axios({
      method: 'delete',
      url: 'http://' + data.hostname + ':8081/deletePrePayment/' + id,
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

function getPrePayment() {
  axios({
    method: 'get',
    url: 'http://localhost:8081/getPrePayment',
    params: {
      page: data.currentPage,
      pageSize: data.pageSize,
      owner: data.owner,
      ownerId: data.ownerId,
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


getPrePayment();

const defaultTime = [
  new Date(2000, 1, 1, 0, 0, 0),
  new Date(2000, 2, 1, 0, 0, 0),
]

</script>
