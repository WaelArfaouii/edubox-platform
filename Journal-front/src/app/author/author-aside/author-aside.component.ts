import { Component } from '@angular/core';

@Component({
  selector: 'app-author-aside',
  templateUrl: './author-aside.component.html',
  styleUrls: ['./author-aside.component.scss']
})
export class AuthorAsideComponent {
  items = [
    { id: 1,icon:'grid_view' , name: 'Dashbord' },
    { id: 2,icon:'edit_note' , name: 'Manuscripts' },
    { id: 3,icon:'add' , name: 'Add Manuscript' },
    { id: 4,icon:'monitoring' , name: 'Updates' },
    { id: 5,icon:'chat' , name: 'Profile' },
    { id: 6,icon:'settings' , name: 'Settings' }
  ];

  selectedItemId: number | null = 1;

  selectItem(itemId: number) {
    this.selectedItemId = itemId;
  }


}
