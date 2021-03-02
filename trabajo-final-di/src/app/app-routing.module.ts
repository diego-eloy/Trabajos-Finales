import { DetalleCicloComponent } from './components/detalle-ciclo/detalle-ciclo.component';
import { CiclosComponent } from './components/ciclos/ciclos.component';
import { AsignaturasComponent } from './components/asignaturas/asignaturas.component';
import { HomeComponent } from './components/home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:"home",component:HomeComponent},
  {path:"asignaturas",component:AsignaturasComponent},
  {path:"ciclos",component:CiclosComponent},
  {path:"ciclos",component:CiclosComponent},
  {path:"detalle/:number/:nombre",component:DetalleCicloComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
