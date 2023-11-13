import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BlogSummaryComponent} from './blog-summary.component';

describe('BlogSummaryComponent', () => {
  let component: BlogSummaryComponent;
  let fixture: ComponentFixture<BlogSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BlogSummaryComponent]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BlogSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
