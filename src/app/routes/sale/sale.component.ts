import { Component, OnInit, Inject } from '@angular/core';
import { NzModalRef, NzMessageService, NZ_MODAL_CONFIG } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SFSchema, SFUISchema, SFSchemaEnumType } from '@delon/form';
import { of, Observable } from 'rxjs';
import { delay, map } from 'rxjs/operators';
import { STColumn, STData } from '@delon/abc';

@Component({
  selector: 'app-routes-sale',
  templateUrl: './sale.component.html',
})
export class RoutesSaleComponent implements OnInit {
  record: any = {};
  i: any;
  schema: SFSchema = {
    properties: {
      mobile: {
        type: 'string',
        title: '手机号',
        format: 'mobile',
      },
      goodsId: {
        type: 'string',
        title: '业务类型',
        ui: {
          widget: 'select',
          asyncData: () => {
            return this.http.get('/goods').pipe(
              map(res => {
                this.data = res['data'];
                return this.data;
              }),
              map(data => {
                data.forEach(el => {
                  el.label = el.name;
                  el.value = el.id;
                });
                return data;
              }),
            );
            // const goods = new Array<SFSchemaEnumType>();
            // this.http.get('/goods').subscribe(res => {
            //   this.data = res['data'];
            //   this.data.forEach(el =>
            //     goods.push({
            //       lable: el.name,
            //       value: el.id,
            //     }),
            //   );
            // });
            // return of(goods).pipe(delay(2000));
            //
            // return this.http.get('/goods').pipe(
            //   map(v => {
            //     return { label: v['name'], value: v['id'] };
            //   }, delay(1200)),
            // );
          },
        },
      },
    },
    required: ['mobile'],
  };
  ui: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: { span: 12 },
    },
  };
  data: STData[];
  columns: STColumn[] = [
    { title: '编号', type: 'no' },
    { title: '名称', index: 'name' },
    { title: '库存', type: 'number', index: 'remain' },
    { title: '添加时间', type: 'date', index: 'addTime' },
  ];

  constructor(
    @Inject(NZ_MODAL_CONFIG) private modal: NzModalRef,
    private msgSrv: NzMessageService,
    public http: _HttpClient,
  ) {}

  ngOnInit(): void {
    // if (this.record.id > 0)
    // this.http.get(`/user/${this.record.id}`).subscribe(res => (this.i = res));
    this.i = {};
  }

  save(value: any) {
    this.http.post('/record', value).subscribe(res => {
      this.msgSrv.success('保存成功');
      // this.modal.close(true);
    });
  }

  close() {
    // this.modal.destroy();
  }
}
