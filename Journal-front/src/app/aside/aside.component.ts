import { Component } from '@angular/core';

@Component({
  selector: 'app-aside',
  templateUrl: './aside.component.html',
  styleUrls: ['./aside.component.scss']
})
export class AsideComponent {
  items = [
    { id: 1,icon:'grid_view' , name: 'Dashbord' },
    { id: 2,icon:'edit_note' , name: 'Assignments' },
    { id: 3,icon:'monitoring' , name: 'Analytics' },
    { id: 4,icon:'chat' , name: 'Invitations' },
    { id: 5,icon:'settings' , name: 'Settings' },
    { id: 6,icon:'add' , name: 'Add Assignment' },

    // Add more items as needed
  ];

  selectedItemId: number | null = 1;

  selectItem(itemId: number) {
    this.selectedItemId = itemId;
  }

}
