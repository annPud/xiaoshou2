import { Component, OnInit, ViewChild } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent, STRes, STData } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { RoutesGoodsEditComponent } from './edit/edit.component';

@Component({
  selector: 'app-routes-goods',
  templateUrl: './goods.component.html',
})
export class RoutesGoodsComponent implements OnInit {
  // url = `/goods`;
  data: STData[];
  searchSchema: SFSchema = {
    properties: {
      name: {
        type: 'string',
        title: '名称搜索',
      },
    },
  };
  @ViewChild('st') st: STComponent;
  columns: STColumn[] = [
    { title: '编号', type: 'no' },
    { title: '名称', index: 'name' },
    { title: '库存', type: 'number', index: 'remain' },
    { title: '添加时间', type: 'date', index: 'addTime' },
    {
      title: '',
      buttons: [
        {
          text: '编辑',
          type: 'static',
          component: RoutesGoodsEditComponent,
          click: 'reload',
        },
        { text: '删除', type: 'del', click: (item: any) => this.del(item.id) },
      ],
    },
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {
    this.load();
  }

  load() {
    this.http.get('/goods').subscribe(res => (this.data = res['data']));
  }
  del(id: string) {
    this.http.delete('/goods/' + id).subscribe(() => this.load());
  }

  ngOnInit() {}

  add() {
    this.modal
      .createStatic(RoutesGoodsEditComponent, { i: { id: 0 } })
      .subscribe(() => this.load());
  }

  search(param) {
    this.http.get('/goods', param).subscribe(res => {
      this.data = res['data'];
    });
  }
}
