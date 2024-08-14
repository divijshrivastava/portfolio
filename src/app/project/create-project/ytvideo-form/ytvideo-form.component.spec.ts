import {ComponentFixture, TestBed} from '@angular/core/testing';

import {YtvideoFormComponent} from './ytvideo-form.component';

describe('YtvideoFormComponent', () => {
  let component: YtvideoFormComponent;
  let fixture: ComponentFixture<YtvideoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [YtvideoFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(YtvideoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
