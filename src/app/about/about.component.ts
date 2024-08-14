import {Component, Inject, OnInit} from '@angular/core';
import {WINDOW} from '../shared/window.token';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {

  constructor(@Inject(WINDOW) private window: Window) {
  }

  ngOnInit(): void {
    addEventListener("scroll", this.animation);
  }

  animation() {
    let scrollY = this.window.scrollY;
    let aboutSection = (document.querySelector(".container") as HTMLElement).offsetTop;
    if (scrollY > aboutSection * 0.9) {

      let aboutContainer = (document.querySelector(".about_container") as HTMLElement);
      let programmerImageContainer = (document.querySelector(".programmer_image_container") as HTMLElement)
      if (aboutContainer) {
        aboutContainer.classList.add("about_container_move_left");
      }

      if (programmerImageContainer) {
        programmerImageContainer
        .classList.add("programmer_image_container_move_right");
      }
    }
  }

}
