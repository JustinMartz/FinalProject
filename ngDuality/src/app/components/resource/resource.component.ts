import { Component } from '@angular/core';
import { Resource } from 'src/app/models/resource';
import { ResourceService } from 'src/app/services/resource.service';

@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css']
})

export class ResourceComponent {

  userResources: Resource[] | undefined;

  constructor(
    private resourceService: ResourceService
  ) {}

  ngOnInit(): void {

            this.resourceService.getUserResources(1).subscribe({
              next: (resources) => {
                this.userResources = resources;
              },
              error: (fail) => {
                console.error('ngOnInit(): Error getting resources');
                console.error(fail);
              }
            });
          }
        }
