import { Component, OnInit } from '@angular/core';
import { SettingsService } from '@delon/theme';
import { settings } from 'cluster';

@Component({
  selector: 'layout-passport',
  templateUrl: './passport.component.html',
  styleUrls: ['./passport.component.less'],
})
export class LayoutPassportComponent implements OnInit {
  title: string;
  desc: string;
  links = [
    {
      title: '帮助',
      href: '',
    },
    {
      title: '隐私',
      href: '',
    },
    {
      title: '条款',
      href: '',
    },
  ];
  constructor(private settingsService: SettingsService) {

  }
  ngOnInit(): void {
    this.title = this.settingsService.app.name;
    this.desc = this.settingsService.app.description;
  }

}
