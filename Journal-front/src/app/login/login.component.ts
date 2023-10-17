import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../services/user/user.service";
import {AuthorService, EditorService, ReviewerService} from "../../journal-api/src";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{

  formLogin!:FormGroup ;
  errorFound:boolean = false ;

  constructor(private fb:FormBuilder , private userService:UserService , private router:Router ,
              private authorService:AuthorService , private editorService:EditorService,
              private reviewerService:ReviewerService ) { }

  ngOnInit(): void {
    this.formLogin = this.fb.group(
      {
        email : this.fb.control("") ,
        password : this.fb.control("")
      }
    )
  }
    handleLogin() {

        this.userService.login(this.formLogin.value).subscribe({
                next: data => {
                    this.errorFound = false ;
                    this.connectUser(data) ;
                    this.userService.getConnectedUser() ;
                }, error: error => {
                    this.errorFound=true ;
                }
            }
        ) ;

    }

    connectUser(data:any){
      this.userService.getUserData(data).subscribe((userData) => {
          this.userService.setConnectedUser(userData);
      });
    }
}
