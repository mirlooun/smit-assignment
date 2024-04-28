import { Injectable, TemplateRef } from '@angular/core';

export interface Toast {
	template: string | TemplateRef<any>;
	classname?: string;
	delay?: number;
}

@Injectable({ providedIn: 'root' })
export class ToastService {
  toasts: Toast[] = [];

  show(toast: Toast) {
    this.toasts.push(toast);
  }

  remove(toast: Toast) {
    this.toasts = this.toasts.filter(t => t !== toast);
  }

  clear() {
    this.toasts.splice(0, this.toasts.length);
  }

  showStandard(template: string | TemplateRef<any>) {
		this.show({ template });
	}

	showSuccess(template: string | TemplateRef<any>, delay = 5000) {
		this.show({ template, classname: 'bg-success text-light', delay });
	}

	showDanger(template: string | TemplateRef<any>, delay = 5000) {
		this.show({ template, classname: 'bg-danger text-light', delay });
	}

	ngOnDestroy(): void {
		this.clear();
	}
}
