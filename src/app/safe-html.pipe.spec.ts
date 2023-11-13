import {SafeHtmlPipe} from './safe-html.pipe';
import {TestBed} from '@angular/core/testing';
import {DomSanitizer} from '@angular/platform-browser';

describe('SafeHtmlPipe', () => {
  let pipe: SafeHtmlPipe;
  let sanitizer: DomSanitizer;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DomSanitizer],
    });

    pipe = TestBed.inject(SafeHtmlPipe);
    sanitizer = TestBed.inject(DomSanitizer);
  });

  it('should create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('should return sanitized HTML', () => {
    const unsafeHtml = '<script>alert("XSS attack!")</script>';
    const safeHtml = sanitizer.bypassSecurityTrustHtml(unsafeHtml);
    expect(pipe.transform(unsafeHtml)).toEqual(safeHtml);
  });
});
