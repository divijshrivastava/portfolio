import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {isPlatformBrowser} from '@angular/common';

import 'prismjs';
import 'prismjs/components/prism-css';
import 'prismjs/components/prism-java';
import 'prismjs/components/prism-javascript';
import 'prismjs/components/prism-markup';
import 'prismjs/components/prism-typescript';

declare var Prism: any;

@Injectable(
  {
    providedIn: 'root',
  },
)
export class HighlightService {

  @Inject(PLATFORM_ID) private readonly platformId: object;

  constructor(@Inject(PLATFORM_ID) platformId: object) {
    this.platformId = platformId;
  }

  public highlightAll(): void {
    if (isPlatformBrowser(this.platformId)) {
      console.log('prismjs highlightingAll');
      Prism.highlightAll();
    }
  }
}
