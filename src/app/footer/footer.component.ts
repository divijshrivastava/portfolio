import {Component, Inject, OnInit} from '@angular/core';
import {WINDOW} from '../shared/window.token';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  public sitemapUlr: string;

  constructor(@Inject(WINDOW) private window: Window) {
    this.sitemapUlr = this.window.location.origin + '/sitemap.xml';
  }

  ngOnInit(): void {
  }

  goToTop() {


    //var currentScroll = document.documentElement.scrollTop || document.body.scrollTop;
    //  if (currentScroll > 0) {
    //  window.requestAnimationFrame(smoothscroll);
    this.window.scrollTo(0, 0);//, currentScroll - (currentScroll / 8));
    // }
  }


}
