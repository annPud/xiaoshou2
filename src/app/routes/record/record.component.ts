import { Component, OnInit, ViewChild } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import {
  STColumn,
  STComponent,
  STData,
  STRes,
  STReq,
  STChange,
  STPage,
} from '@delon/abc';
import { SFSchema } from '@delon/form';

@Component({
  selector: 'app-routes-record',
  templateUrl: './record.component.html',
})
export class RoutesRecordComponent implements OnInit {
  data: STData[];
  // url: `/record?total=100`;
  // res: STRes = {
  //   process: data => {
  //     console.log(data);
  //     return data;
  //   },
  // };
  // req: STReq = {
  //   method: 'get',
  // };
  pi = 1;
  ps = 10;
  total = 0;
  page: STPage = { front: false };
  searchParam = {};
  searchSchema: SFSchema = {
    properties: {
      mobile: {
        type: 'string',
        title: '贫困户',
      },
    },
  };
  @ViewChild('st') st: STComponent;
  columns: STColumn[] = [
    { title: '编号', type: 'no' },
    { title: '贫困户', index: 'mobile' },
    { title: '拨款项目', index: 'goodsName' },
    { title: '时间', type: 'date', index: 'time' },
    { title: '帮扶人', index: 'salerName' },
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {}

  ngOnInit() {
    this.http.get('/record').subscribe(res => {
      this.data = res['data'];
      this.total = res['total'];
    });
  }
  search(param) {
    this.searchParam = param;
    this.http.get('/record', param).subscribe(res => {
      this.data = res['data'];
      this.total = res['total'];
    });
  }

  reset(param) {
    this.searchParam = {};
    this.http.get('/record').subscribe(res => {
      this.data = res['data'];
      this.total = res['total'];
    });
  }
  change(param: STChange) {
    if ('pi' === param.type) {
      const params = param;
      if (this.searchParam) {
        for (const key in this.searchParam) {
          if (this.searchParam.hasOwnProperty(key)) {
            const element = this.searchParam[key];
            params[key] = element;
          }
        }
      }
      this.http.get('/record', params).subscribe(res => {
        this.data = res['data'];
        this.total = res['total'];
      });
    }
  }
}
