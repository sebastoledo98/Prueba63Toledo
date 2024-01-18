import { Component } from '@angular/core';
import { Deudas } from 'src/app/domain/deudas';
import { DeudasService } from 'src/app/servicios/deudas.service';

@Component({
  selector: 'app-deudas',
  templateUrl: './deudas.component.html',
  styleUrls: ['./deudas.component.css']
})
export class DeudasComponent {

  deudas: any;

  deuda: Deudas = new Deudas();

  constructor(private deudasService: DeudasService){

  }

  ngOnInit():void {
    this.deudas = this.deudasService.getDeudas();
  }

  buscar(){
    this.deudasService.buscarDeudas(this.deuda).subscribe(data => {
      console.log(data);
      if(data.codigo == 1)
        this.ngOnInit();
      else
        alert("Error al insertar " + data.mensaje);
    });
  }
}
