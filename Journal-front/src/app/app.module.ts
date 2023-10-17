import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AsideComponent } from './aside/aside.component';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UpdatesComponent } from './updates/updates.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { AuthorComponent } from './author/author.component';
import { EditorComponent } from './editor/editor.component';
import { ReviewerComponent } from './reviewer/reviewer.component';
import { SubmitManuscriptComponent } from './submit-manuscript/submit-manuscript.component';
import { AllManuscriptsComponent } from './all-manuscripts/all-manuscripts.component';
import { ProfileComponent } from './profile/profile.component';
import { AuthorAsideComponent } from './author/author-aside/author-aside.component';
import { AuthorDashboardComponent } from './author/author-dashboard/author-dashboard.component';
import { ManuscriptsComponent } from './author/manuscripts/manuscripts.component';
import { SettingsComponent } from './settings/settings.component';
import { AuthorUpdatesComponent } from './author/author-updates/author-updates.component';

@NgModule({
  declarations: [
    AppComponent,
    AsideComponent,
    LoginComponent,
    MainComponent,
    SignUpComponent,
    UpdatesComponent,
    AuthorComponent,
    EditorComponent,
    ReviewerComponent,
    SubmitManuscriptComponent,
    AllManuscriptsComponent,
    ProfileComponent,
    AuthorAsideComponent,
    AuthorDashboardComponent,
    ManuscriptsComponent,
    SettingsComponent,
    AuthorUpdatesComponent
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        ReactiveFormsModule,
        FormsModule
    ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
