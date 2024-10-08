import {Component} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {UtilService} from '../../services/util.service';

interface ProjectType {
  name: string;
  value: string;
  formGroup: FormGroup;
}

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.scss']
})
export class CreateProjectComponent {

  public selectedProjectType: string;

  public projectTypes: ProjectType[];

  constructor(private util: UtilService) {
    this.projectTypes = [{
      name: 'Youtube Video',
      value: 'YTVIDEO',
      formGroup: new FormGroup(''),
    }
      ,
      {
        name: 'Website',
        value: 'WEBSITE',
        formGroup: new FormGroup(''),
      },
      {
        name: 'Github Code',
        value: 'CODE',
        formGroup: new FormGroup(''),
      },

    ];

    this.selectedProjectType = this.projectTypes[0].value;
  }

  ngOnInit() {
    this.util.heading.next({title: 'Project', url: '/project'});
    this.util.loader.next({state: 'off'});
  }

  onItemChange(projectType: any) {
    console.log(projectType);
    console.log(this.selectedProjectType);
  }
}
