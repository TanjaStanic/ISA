import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AviocompanySService } from '../../services/aviocompany-s.service';


@Component({
  selector: 'app-profilcompany',
  templateUrl: './profilcompany.component.html',
  styleUrls: ['./profilcompany.component.css']
})
export class ProfilcompanyComponent implements OnInit {
        
      private currentCompany: any;
      private addDestination = false;

  constructor(private router: Router, private data : AviocompanySService) { }

  ngOnInit() {
      
       this.data.currentCompany.subscribe(
      currentCompany => 
      {
        this.currentCompany = currentCompany;
        console.log(currentCompany);
  }
           
    );
      }

  saveAviocompany() {
    this.data.addAviocompany(this.currentCompany).subscribe(airlineNew => {
      console.log(this.currentCompany);

      this.router.navigate(['/avioCompany'], {});
    });
  }

  destinacije() {
    this.addDestination = true;
  }
}
