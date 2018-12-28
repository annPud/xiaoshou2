import { Component, OnInit, ViewChild } from '@angular/core';
import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SFSchema, SFUISchema } from '@delon/form';

@Component({
  selector: 'app-routes-user-edit',
  templateUrl: './edit.component.html',
})
export class RoutesUserEditComponent implements OnInit {
  record: any = {};
  i: any;
  schema: SFSchema = {
    properties: {
      name: { type: 'string', title: '姓名' },
      userName: { type: 'string', title: '登陆名' },
      password: { type: 'string', title: '密码' },
      email: { type: 'string', title: '邮箱' },
      role: {
        type: 'string',
        title: '角色',
        enum: [
          { label: '销售员', value: 'SALER' },
          { label: '管理员', value: 'ADMIN' },
        ],
        default: 'SALER',
        ui: { widget: 'select' },
      },
    },
    required: ['name', 'userName'],
  };
  ui: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: { span: 12 },
    },
  };

  constructor(
    private modal: NzModalRef,
    private msgSrv: NzMessageService,
    public http: _HttpClient,
  ) {}

  ngOnInit(): void {
    if (this.record.id) {
      this.http.get(`/user/${this.record.id}`).subscribe(res => (this.i = res));
    }
  }

  save(value: any) {
    if (this.record.id) {
      this.http.patch('/user', value).subscribe(res => {
        this.msgSrv.success('修改成功');
        this.modal.close(true);
      });
    } else {
      this.http.post('/user', value).subscribe(res => {
        this.msgSrv.success('添加成功');
        this.modal.close(true);
      });
    }
  }

  close() {
    this.modal.destroy();
  }
}
