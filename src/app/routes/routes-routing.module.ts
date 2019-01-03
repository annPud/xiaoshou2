import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SimpleGuard } from '@delon/auth';
import { environment } from '@env/environment';
// layout
import { LayoutDefaultComponent } from '../layout/default/default.component';
import { LayoutFullScreenComponent } from '../layout/fullscreen/fullscreen.component';
import { LayoutPassportComponent } from '../layout/passport/passport.component';
// dashboard pages
import { DashboardComponent } from './dashboard/dashboard.component';
// passport pages
import { UserLoginComponent } from './passport/login/login.component';
import { UserRegisterComponent } from './passport/register/register.component';
import { UserRegisterResultComponent } from './passport/register-result/register-result.component';
// single pages
import { CallbackComponent } from './callback/callback.component';
import { UserLockComponent } from './passport/lock/lock.component';
import { Exception403Component } from './exception/403.component';
import { Exception404Component } from './exception/404.component';
import { Exception500Component } from './exception/500.component';
import { RoutesRecordComponent } from './record/record.component';
import { RoutesSaleComponent } from './sale/sale.component';
import { RoutesGoodsComponent } from './goods/goods.component';
import { RoutesUserComponent } from './user/user.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutDefaultComponent,
    canActivate: [SimpleGuard],
    children: [
      { path: '', redirectTo: 'sale', pathMatch: 'full' },
      {
        path: 'dashboard',
        component: DashboardComponent,
        data: { title: '仪表盘', titleI18n: 'dashboard' },
      },
      // 业务子模块
      // // { path: 'widgets', loadChildren: './widgets/widgets.module#WidgetsModule' }
      {
        path: 'sale',
        component: RoutesSaleComponent,
        data: { title: '销售业务' },
      },
      {
        path: 'record',
        component: RoutesRecordComponent,
        data: { title: '记录' },
      },
      {
        path: 'goods',
        component: RoutesGoodsComponent,
        data: { title: '业务管理' },
      },
      {
        path: 'user',
        component: RoutesUserComponent,
        data: { title: '业务管理' },
      },
    ],
  },
  // 全屏布局
  // {
  //     path: 'fullscreen',
  //     component: LayoutFullScreenComponent,
  //     children: [
  //     ]
  // },
  // passport
  {
    path: 'passport',
    component: LayoutPassportComponent,
    children: [
      {
        path: 'login',
        component: UserLoginComponent,
        data: { title: '登录', titleI18n: 'pro-login' },
      },
      {
        path: 'register',
        component: UserRegisterComponent,
        data: { title: '注册', titleI18n: 'pro-register' },
      },
      {
        path: 'register-result',
        component: UserRegisterResultComponent,
        data: { title: '注册结果', titleI18n: 'pro-register-result' },
      },
    ],
  },
  // 单页不包裹Layout
  { path: 'callback/:type', component: CallbackComponent },
  {
    path: 'lock',
    component: UserLockComponent,
    data: { title: '锁屏', titleI18n: 'lock' },
  },
  { path: '403', component: Exception403Component },
  { path: '404', component: Exception404Component },
  { path: '500', component: Exception500Component },
  { path: '**', redirectTo: 'dashboard' },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      useHash: environment.useHash,
      // true,
    }),
  ],
  exports: [RouterModule],
})
export class RouteRoutingModule {}
