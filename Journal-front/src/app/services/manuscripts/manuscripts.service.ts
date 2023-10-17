import { Injectable } from '@angular/core';
import {ManuscriptDTO, ManuscriptService} from "../../../journal-api/src";
import {UserService} from "../user/user.service";

@Injectable({
  providedIn: 'root'
})
export class ManuscriptsService {

  constructor(private manuscriptService:ManuscriptService , private userService:UserService) { }

  saveManuscript(manuscript:ManuscriptDTO){
    let id = this.userService.getConnectedUser().id ;
    this.manuscriptService.saveManuscript(id, manuscript) ;
  }
  getManuscriptsByAuthor(){
    let id = this.userService.getConnectedUser().id ;
    this.manuscriptService.getManuscripts(id) ;
  }

}
