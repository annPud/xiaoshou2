import { Component, OnInit, ViewChild } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent, STData } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { RoutesUserEditComponent } from './edit/edit.component';

@Component({
  selector: 'app-routes-user',
  templateUrl: './user.component.html',
})
export class RoutesUserComponent implements OnInit {
  // url = `/user`;
  data: STData[];
  searchSchema: SFSchema = {
    properties: {
      name: {
        type: 'string',
        title: '姓名',
      },
    },
  };
  @ViewChild('st') st: STComponent;
  columns: STColumn[] = [
    { title: '编号', type: 'no' },
    { title: '姓名', index: 'name' },
    { title: '手机号码', index: 'userName' },
    { title: '邮箱', index: 'email' },
    {
      title: '角色',
      index: 'role',
      type: 'tag',
      tag: {
        ADMIN: { text: '管理员', color: 'green' },
        SALER: { text: '帮扶人', color: 'blue' },
      },
    },
    {
      title: '',
      buttons: [
        {
          text: '编辑',
          type: 'modal',
          modal: { component: RoutesUserEditComponent },
          click: () => this.load(),
        },
        { text: '删除', type: 'del', click: (item: any) => this.del(item.id) },
      ],
    },
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {}

  ngOnInit() {
    this.load();
  }

  load() {
    this.http.get('/user').subscribe(res => (this.data = res['data']));
  }
  del(id: string) {
    this.http.delete(`/user/${id}`).subscribe(() => this.load());
  }

  add() {
    this.modal
      .createStatic(RoutesUserEditComponent, { i: { id: 0 } })
      .subscribe(() => this.load());
  }

  search(param) {
    this.http.get('/user', param).subscribe(res => {
      this.data = res['data'];
    });
  }
}
