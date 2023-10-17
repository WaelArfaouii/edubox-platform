import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {
    AuthenticationRequest,
    AuthenticationResponse,
    AuthenticationService,
    AuthorDTO, AuthorService, EditorDTO, EditorService, ReviewerDTO, ReviewerService
} from "../../../journal-api/src";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import jwtDecode from "jwt-decode";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    token!: string;
    isRevivewerAuthenticated: boolean =false;
    isEditorAuthenticated: boolean =false;
    isAuthorAuthenticated: boolean =false;
    email!: string;
    roles!: any;

    constructor(private authenticationService: AuthenticationService, private http: HttpClient,
                private authorService: AuthorService, private editorService: EditorService,
                private reviewerService: ReviewerService
    ) {
    }

    login(authenticationRequest: AuthenticationRequest) {
       return  this.authenticationService.authenticate(authenticationRequest);
    }
    getUserData(data: any): Observable<any> {
        return new Observable((observer) => {
            let specs: any = this.getUserEmailAndRole(data);
            let email: string = specs.email;
            const encodedEmail = { "email": email };
            let role: string = specs.roles[0];

            switch (role) {
                case 'AUTHOR':
                  this.isRevivewerAuthenticated=false;
                  this.isEditorAuthenticated=false;
                  this.isAuthorAuthenticated=true;
                    this.authorService.getAuthorByEmail(encodedEmail).subscribe((auth: AuthorDTO) => {
                        observer.next(auth);
                        observer.complete();
                    });
                    break;

                case 'EDITOR':
                    this.isRevivewerAuthenticated=false;
                    this.isEditorAuthenticated=true;
                    this.isAuthorAuthenticated=false;
                    this.editorService.getEditorByEmail(encodedEmail).subscribe((edit: EditorDTO) => {
                        observer.next(edit);
                        observer.complete();
                    });
                    break;

                case 'REVIEWER':
                    this.isRevivewerAuthenticated=true;
                    this.isEditorAuthenticated=false;
                    this.isAuthorAuthenticated=false;
                    this.reviewerService.getReviewerByEmail(encodedEmail).subscribe((rev: ReviewerDTO) => {
                        observer.next(rev);
                        observer.complete();
                    });
                    break;

                default:
                    observer.error('Invalid role'); // You can handle this case as needed
            }
        });
    }

    isAuthorLogged(){
      return this.isAuthorAuthenticated ;
    }

    isEditorLogged(){
      return this.isEditorAuthenticated ;
    }

    isReviewerLogged(){
      return this.isRevivewerAuthenticated ;
    }

    getUserEmailAndRole(authenticationResponse: AuthenticationResponse) {
        this.token = authenticationResponse['accessToken'] ?? '';
        let decodedJwt: any = jwtDecode(this.token);
        this.email = decodedJwt.sub;
        this.roles = decodedJwt.role;
        return {
            'email': this.email,
            'roles': this.roles
        }
    }

  setConnectedUser(user: any): void {
    localStorage.setItem('connectedUser', JSON.stringify(user));
    }
   getConnectedUser() {
     const storedUserString = localStorage.getItem('connectedUser');
     if (storedUserString) {
       const storedUser = JSON.parse(storedUserString);
       console.log(storedUser) ;
       return storedUser ;
     } else {
       console.log('No user data found in localStorage');
     }
    }
}
