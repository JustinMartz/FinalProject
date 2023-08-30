import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { UserhomeComponent } from './components/userhome/userhome.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { DualityhomeComponent } from './components/dualityhome/dualityhome.component';
import { PostComponent } from './components/post/post.component';
import { SinglePostComponent } from './components/single-post/single-post.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { DirectMessageComponent } from './components/direct-message/direct-message.component';
import { ResourceComponent } from './components/resource/resource.component';

const routes: Routes =  [
  { path: '', pathMatch: 'full', redirectTo: 'dualityhome' },
  { path: 'posts/:postId',component: SinglePostComponent },
  { path: 'dualityhome', component: DualityhomeComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'userhome', component: UserhomeComponent},
  { path: 'users/:userId', component: UserProfileComponent},
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LogoutComponent},
  { path: 'posts', component: PostComponent},
  { path: 'directMessages', component: DirectMessageComponent},
  { path: 'resources', component: ResourceComponent},
  { path: '**', component: NotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
