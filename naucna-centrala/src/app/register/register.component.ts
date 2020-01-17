import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user-service/user.service';
import { RepositoryService } from '../services/repository-service/repository.service';
import { AreaService } from '../services/areas-service/area.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private categories = [];
  private formFieldsDto = null;
  private formFields = [];

  constructor(private userService: UserService, private areaService: AreaService, private repositoryService: RepositoryService) {
    repositoryService.startProcess().subscribe(
      res => {
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.areaService.loadAreas().subscribe(
          res => {
            this.categories = res;
           }
        );
      }
    );
  }

  ngOnInit() {
  }

  onSubmit(value, form) {
    const o = new Array();
    for (const property in value) {
     if (property != 'branches' ) {
      o.push({fieldId : property, fieldValue : value[property]});
      } else {
       o.push({fieldId : property, categories : value[property]});
     }
    }
    this.userService.registerUser(o, this.formFieldsDto.taskId).subscribe(
      res => {
        window.location.href = 'login';
      }
    );
  }
}
