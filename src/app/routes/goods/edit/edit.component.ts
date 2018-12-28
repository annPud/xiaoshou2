import { Component, OnInit, ViewChild } from '@angular/core';
import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SFSchema, SFUISchema } from '@delon/form';

@Component({
  selector: 'app-routes-goods-edit',
  templateUrl: './edit.component.html',
})
export class RoutesGoodsEditComponent implements OnInit {
  record: any = {};
  i: any;
  schema: SFSchema = {
    properties: {
      id: { type: 'string', title: '编号' },
      name: { type: 'string', title: '名称', maxLength: 15 },
      remain: { type: 'number', title: '库存' },
    },
    required: ['name', 'remain'],
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
    // if (this.record.id > 0)
    //   this.http.get(`/user/${this.record.id}`).subscribe(res => (this.i = res));
    if (!this.record.id) {
      delete this.schema.properties.id;
    }
  }

  save(value: any) {
    this.http.post('/goods', value).subscribe(res => {
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    });
  }

  close() {
    this.modal.destroy();
  }
}
