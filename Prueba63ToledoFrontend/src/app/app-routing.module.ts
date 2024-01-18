import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DeudasComponent } from 'src/app/paginas/deudas/deudas.component';

const routes: Routes = [
  {path: 'paginas/deduas', component: DeudasComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
