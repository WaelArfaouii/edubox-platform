import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignUpComponent} from "./sign-up/sign-up.component";
import {LoginComponent} from "./login/login.component";
import {AuthorComponent} from "./author/author.component";
import {AuthorGuardService} from "./services/guards/authorGuard/author-guard.service";
import {ProfileComponent} from "./profile/profile.component";
import {AuthorDashboardComponent} from "./author/author-dashboard/author-dashboard.component";
import {SubmitManuscriptComponent} from "./author/submit-manuscript/submit-manuscript.component";
import {AllManuscriptsComponent} from "./author/all-manuscripts/all-manuscripts.component";
import {AuthorUpdatesComponent} from "./author/author-updates/author-updates.component";
import {SettingsComponent} from "./settings/settings.component";

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'SignUp', component: SignUpComponent},
  // {
  //   path: 'editor',
  //   component: EditorComponent,
  //   canActivate: [EditorGuardService],
  //   children: [
  //     { path: 'dashboard', component: MainComponent },
  //     { path: 'manuscripts', component: MainComponent },
  //     { path: 'reviewers', component: MainComponent },
  //     { path: 'manuscriptsControl', component: MainComponent },
  //     { path: 'profile', component: ProfileComponent },
  //   ],
  // },

  {path: 'author', component: AuthorComponent , canActivate : [AuthorGuardService] , children :[
      {path: 'dashboard', component: AuthorDashboardComponent },
      {path: 'manuscripts', component: AllManuscriptsComponent },
      {path: 'submit', component: SubmitManuscriptComponent },
      {path: 'updates', component: AuthorUpdatesComponent },
      {path: 'profile', component: ProfileComponent } ,
      {path: 'settings', component: SettingsComponent } ]} ,

  // {path: 'reviewer', component: ReviewerComponent , canActivate : [ReviewerGuardService] , children :[
  //     {path: 'dashboard', component: MainComponent },
  //     {path: 'invitations', component: EditorComponent },
  //     {path: 'manuscriptsReviewed', component: EditorComponent },
  //     {path: 'profile', component: ProfileComponent } ]
  //  }

]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
