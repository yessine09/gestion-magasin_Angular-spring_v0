import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListFactureComponent } from './list-facture/list-facture.component';


const routes: Routes = [
  {path:'AdminListFacture', component:ListFactureComponent}

 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminLayoutRoutingModule { }
