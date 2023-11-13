export class UserActivity {
  id: number;
  ipAddress: string;
  activityType: string;
  activityDetails: string;
  activityTime: string;
  userAgentString: string;
  browser: string;
  platform: string;
  deviceType: string;
  platformMaker: string;

  constructor() {
    this.id = -1;
    this.ipAddress = '';
    this.activityType = '';
    this.activityDetails = '';
    this.activityTime = '';
    this.userAgentString = '';
    this.browser = '';
    this.platform = '';
    this.deviceType = '';
    this.platformMaker = '';
  }
}
