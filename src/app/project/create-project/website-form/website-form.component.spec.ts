import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WebsiteFormComponent} from './website-form.component';

describe('WebsiteFormComponent', () => {
  let component: WebsiteFormComponent;
  let fixture: ComponentFixture<WebsiteFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WebsiteFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WebsiteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
