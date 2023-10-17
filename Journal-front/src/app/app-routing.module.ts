import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SignUpComponent} from "./sign-up/sign-up.component";
import {LoginComponent} from "./login/login.component";
import {EditorComponent} from "./editor/editor.component";
import {ReviewerComponent} from "./reviewer/reviewer.component";
import {AuthorComponent} from "./author/author.component";
import {EditorGuardService} from "./services/guards/editorGuard/editor-guard.service";
import {AuthorGuardService} from "./services/guards/authorGuard/author-guard.service";
import {ReviewerGuardService} from "./services/guards/reviewerGuard/reviewer-guard.service";
import {MainComponent} from "./main/main.component";
import {AllManuscriptsComponent} from "./all-manuscripts/all-manuscripts.component";
import {SubmitManuscriptComponent} from "./submit-manuscript/submit-manuscript.component";
import {ProfileComponent} from "./profile/profile.component";

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'SignUp', component: SignUpComponent},
  {
    path: 'editor',
    component: EditorComponent,
    canActivate: [EditorGuardService],
    children: [
      { path: 'dashboard', component: MainComponent },
      { path: 'manuscripts', component: MainComponent },
      { path: 'reviewers', component: MainComponent },
      { path: 'manuscriptsControl', component: MainComponent },
      { path: 'profile', component: ProfileComponent },
    ],
  },

  {path: 'author', component: AuthorComponent , canActivate : [AuthorGuardService] , children :[
      {path: 'dashboard', component: MainComponent },
      {path: 'submit', component: SubmitManuscriptComponent },
      {path: 'manuscripts', component: AllManuscriptsComponent },
      {path: 'newManuscript', component: EditorComponent },
      {path: 'profile', component: ProfileComponent } ]},

  {path: 'reviewer', component: ReviewerComponent , canActivate : [ReviewerGuardService] , children :[
      {path: 'dashboard', component: MainComponent },
      {path: 'invitations', component: EditorComponent },
      {path: 'manuscriptsReviewed', component: EditorComponent },
      {path: 'profile', component: ProfileComponent } ]
   }

]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
