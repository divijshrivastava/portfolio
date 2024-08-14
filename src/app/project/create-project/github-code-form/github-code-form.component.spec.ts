import {ComponentFixture, TestBed} from '@angular/core/testing';

import {GithubCodeFormComponent} from './github-code-form.component';

describe('GithubCodeFormComponent', () => {
  let component: GithubCodeFormComponent;
  let fixture: ComponentFixture<GithubCodeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GithubCodeFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GithubCodeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
