import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-author-aside',
  templateUrl: './author-aside.component.html',
  styleUrls: ['./author-aside.component.scss']
})
export class AuthorAsideComponent implements OnInit{

  constructor(private route:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.router.navigate(['dashboard'],{relativeTo:this.route});
  }

  items = [
    { id: 1,icon:'grid_view' , name: 'Dashbord' , url:'dashboard'},
    { id: 2,icon:'edit_note' , name: 'Manuscripts', url:'manuscripts' },
    { id: 3,icon:'add' , name: 'Add Manuscript', url:'submit'},
    { id: 4,icon:'monitoring' , name: 'Updates' , url:'updates'},
    { id: 5,icon:'chat' , name: 'Profile', url:'profile' },
    { id: 6,icon:'settings' , name: 'Settings', url:'settings' }
  ];

  selectedItemId: number | null = 1;

  selectItem(itemId: number) {
    this.selectedItemId = itemId;
  }

  onPathClick(url:string , itemId:number){
    this.selectItem(itemId);
    this.router.navigate([url],{relativeTo:this.route}); }






}
