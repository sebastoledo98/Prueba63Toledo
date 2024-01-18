import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Deudas } from 'src/app/domain/deudas';

@Injectable({
  providedIn: 'root'
})
export class DeudasService {

  constructor(private http: HttpClient) { }

  getDeudas(){
    let url = environment.WS_PATH + "/deudas/list";
    return this.http.get<any>(url);
  }

  buscarDeudas(deuda: Deudas){
    let url = environment.WS_PATH + "/deudas?dni=" + deuda.dni;
    return this.http.get<any>(url);
  }

  saveDeudas(deuda: Deudas){
    let url = environment.WS_PATH + "/deudas";
    return this.http.post<any>(url, deuda);
  }
}
