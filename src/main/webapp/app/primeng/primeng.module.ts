import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { CwcrmButtonDemoModule } from './buttons/button/buttondemo.module';
import { CwcrmSplitbuttonDemoModule } from './buttons/splitbutton/splitbuttondemo.module';

import { CwcrmDialogDemoModule } from './overlay/dialog/dialogdemo.module';
import { CwcrmConfirmDialogDemoModule } from './overlay/confirmdialog/confirmdialogdemo.module';
import { CwcrmLightboxDemoModule } from './overlay/lightbox/lightboxdemo.module';
import { CwcrmTooltipDemoModule } from './overlay/tooltip/tooltipdemo.module';
import { CwcrmOverlayPanelDemoModule } from './overlay/overlaypanel/overlaypaneldemo.module';
import { CwcrmSideBarDemoModule } from './overlay/sidebar/sidebardemo.module';

import { CwcrmKeyFilterDemoModule } from './inputs/keyfilter/keyfilterdemo.module';
import { CwcrmInputTextDemoModule } from './inputs/inputtext/inputtextdemo.module';
import { CwcrmInputTextAreaDemoModule } from './inputs/inputtextarea/inputtextareademo.module';
import { CwcrmInputGroupDemoModule } from './inputs/inputgroup/inputgroupdemo.module';
import { CwcrmCalendarDemoModule } from './inputs/calendar/calendardemo.module';
import { CwcrmCheckboxDemoModule } from './inputs/checkbox/checkboxdemo.module';
import { CwcrmChipsDemoModule } from './inputs/chips/chipsdemo.module';
import { CwcrmColorPickerDemoModule } from './inputs/colorpicker/colorpickerdemo.module';
import { CwcrmInputMaskDemoModule } from './inputs/inputmask/inputmaskdemo.module';
import { CwcrmInputSwitchDemoModule } from './inputs/inputswitch/inputswitchdemo.module';
import { CwcrmPasswordIndicatorDemoModule } from './inputs/passwordindicator/passwordindicatordemo.module';
import { CwcrmAutoCompleteDemoModule } from './inputs/autocomplete/autocompletedemo.module';
import { CwcrmSliderDemoModule } from './inputs/slider/sliderdemo.module';
import { CwcrmSpinnerDemoModule } from './inputs/spinner/spinnerdemo.module';
import { CwcrmRatingDemoModule } from './inputs/rating/ratingdemo.module';
import { CwcrmSelectDemoModule } from './inputs/select/selectdemo.module';
import { CwcrmSelectButtonDemoModule } from './inputs/selectbutton/selectbuttondemo.module';
import { CwcrmListboxDemoModule } from './inputs/listbox/listboxdemo.module';
import { CwcrmRadioButtonDemoModule } from './inputs/radiobutton/radiobuttondemo.module';
import { CwcrmToggleButtonDemoModule } from './inputs/togglebutton/togglebuttondemo.module';
import { CwcrmEditorDemoModule } from './inputs/editor/editordemo.module';

import { CwcrmGrowlDemoModule } from './messages/growl/growldemo.module';
import { CwcrmMessagesDemoModule } from './messages/messages/messagesdemo.module';
/*import { CwcrmToastDemoModule } from './messages/toast/toastdemo.module';*/
import { CwcrmGalleriaDemoModule } from './multimedia/galleria/galleriademo.module';

import { CwcrmFileUploadDemoModule } from './fileupload/fileupload/fileuploaddemo.module';

import { CwcrmAccordionDemoModule } from './panel/accordion/accordiondemo.module';
import { CwcrmPanelDemoModule } from './panel/panel/paneldemo.module';
import { CwcrmTabViewDemoModule } from './panel/tabview/tabviewdemo.module';
import { CwcrmFieldsetDemoModule } from './panel/fieldset/fieldsetdemo.module';
import { CwcrmToolbarDemoModule } from './panel/toolbar/toolbardemo.module';
import { CwcrmGridDemoModule } from './panel/grid/griddemo.module';
import { CwcrmScrollPanelDemoModule } from './panel/scrollpanel/scrollpaneldemo.module';
import { CwcrmCardDemoModule } from './panel/card/carddemo.module';

import { CwcrmDataTableDemoModule } from './data/datatable/datatabledemo.module';
import { CwcrmTableDemoModule } from './data/table/tabledemo.module';
import { CwcrmDataGridDemoModule } from './data/datagrid/datagriddemo.module';
import { CwcrmDataListDemoModule } from './data/datalist/datalistdemo.module';
import { CwcrmDataScrollerDemoModule } from './data/datascroller/datascrollerdemo.module';
import { CwcrmPickListDemoModule } from './data/picklist/picklistdemo.module';
import { CwcrmOrderListDemoModule } from './data/orderlist/orderlistdemo.module';
import { CwcrmScheduleDemoModule } from './data/schedule/scheduledemo.module';
import { CwcrmTreeDemoModule } from './data/tree/treedemo.module';
import { CwcrmTreeTableDemoModule } from './data/treetable/treetabledemo.module';
import { CwcrmPaginatorDemoModule } from './data/paginator/paginatordemo.module';
import { CwcrmGmapDemoModule } from './data/gmap/gmapdemo.module';
import { CwcrmOrgChartDemoModule } from './data/orgchart/orgchartdemo.module';
import { CwcrmCarouselDemoModule } from './data/carousel/carouseldemo.module';
import { CwcrmDataViewDemoModule } from './data/dataview/dataviewdemo.module';

import { CwcrmBarchartDemoModule } from './charts/barchart/barchartdemo.module';
import { CwcrmDoughnutchartDemoModule } from './charts/doughnutchart/doughnutchartdemo.module';
import { CwcrmLinechartDemoModule } from './charts/linechart/linechartdemo.module';
import { CwcrmPiechartDemoModule } from './charts/piechart/piechartdemo.module';
import { CwcrmPolarareachartDemoModule } from './charts/polarareachart/polarareachartdemo.module';
import { CwcrmRadarchartDemoModule } from './charts/radarchart/radarchartdemo.module';

import { CwcrmDragDropDemoModule } from './dragdrop/dragdrop/dragdropdemo.module';

import { CwcrmMenuDemoModule } from './menu/menu/menudemo.module';
import { CwcrmContextMenuDemoModule } from './menu/contextmenu/contextmenudemo.module';
import { CwcrmPanelMenuDemoModule } from './menu/panelmenu/panelmenudemo.module';
import { CwcrmStepsDemoModule } from './menu/steps/stepsdemo.module';
import { CwcrmTieredMenuDemoModule } from './menu/tieredmenu/tieredmenudemo.module';
import { CwcrmBreadcrumbDemoModule } from './menu/breadcrumb/breadcrumbdemo.module';
import { CwcrmMegaMenuDemoModule } from './menu/megamenu/megamenudemo.module';
import { CwcrmMenuBarDemoModule } from './menu/menubar/menubardemo.module';
import { CwcrmSlideMenuDemoModule } from './menu/slidemenu/slidemenudemo.module';
import { CwcrmTabMenuDemoModule } from './menu/tabmenu/tabmenudemo.module';

import { CwcrmBlockUIDemoModule } from './misc/blockui/blockuidemo.module';
import { CwcrmCaptchaDemoModule } from './misc/captcha/captchademo.module';
import { CwcrmDeferDemoModule } from './misc/defer/deferdemo.module';
import { CwcrmInplaceDemoModule } from './misc/inplace/inplacedemo.module';
import { CwcrmProgressBarDemoModule } from './misc/progressbar/progressbardemo.module';
import { CwcrmRTLDemoModule } from './misc/rtl/rtldemo.module';
import { CwcrmTerminalDemoModule } from './misc/terminal/terminaldemo.module';
import { CwcrmValidationDemoModule } from './misc/validation/validationdemo.module';
import { CwcrmProgressSpinnerDemoModule } from './misc/progressspinner/progressspinnerdemo.module';

@NgModule({
    imports: [
        CwcrmMenuDemoModule,
        CwcrmContextMenuDemoModule,
        CwcrmPanelMenuDemoModule,
        CwcrmStepsDemoModule,
        CwcrmTieredMenuDemoModule,
        CwcrmBreadcrumbDemoModule,
        CwcrmMegaMenuDemoModule,
        CwcrmMenuBarDemoModule,
        CwcrmSlideMenuDemoModule,
        CwcrmTabMenuDemoModule,

        CwcrmBlockUIDemoModule,
        CwcrmCaptchaDemoModule,
        CwcrmDeferDemoModule,
        CwcrmInplaceDemoModule,
        CwcrmProgressBarDemoModule,
        CwcrmInputMaskDemoModule,
        CwcrmRTLDemoModule,
        CwcrmTerminalDemoModule,
        CwcrmValidationDemoModule,

        CwcrmButtonDemoModule,
        CwcrmSplitbuttonDemoModule,

        CwcrmInputTextDemoModule,
        CwcrmInputTextAreaDemoModule,
        CwcrmInputGroupDemoModule,
        CwcrmCalendarDemoModule,
        CwcrmChipsDemoModule,
        CwcrmInputMaskDemoModule,
        CwcrmInputSwitchDemoModule,
        CwcrmPasswordIndicatorDemoModule,
        CwcrmAutoCompleteDemoModule,
        CwcrmSliderDemoModule,
        CwcrmSpinnerDemoModule,
        CwcrmRatingDemoModule,
        CwcrmSelectDemoModule,
        CwcrmSelectButtonDemoModule,
        CwcrmListboxDemoModule,
        CwcrmRadioButtonDemoModule,
        CwcrmToggleButtonDemoModule,
        CwcrmEditorDemoModule,
        CwcrmColorPickerDemoModule,
        CwcrmCheckboxDemoModule,
        CwcrmKeyFilterDemoModule,

        CwcrmGrowlDemoModule,
        CwcrmMessagesDemoModule,
        /*CwcrmToastDemoModule,*/
        CwcrmGalleriaDemoModule,

        CwcrmFileUploadDemoModule,

        CwcrmAccordionDemoModule,
        CwcrmPanelDemoModule,
        CwcrmTabViewDemoModule,
        CwcrmFieldsetDemoModule,
        CwcrmToolbarDemoModule,
        CwcrmGridDemoModule,
        CwcrmScrollPanelDemoModule,
        CwcrmCardDemoModule,

        CwcrmBarchartDemoModule,
        CwcrmDoughnutchartDemoModule,
        CwcrmLinechartDemoModule,
        CwcrmPiechartDemoModule,
        CwcrmPolarareachartDemoModule,
        CwcrmRadarchartDemoModule,

        CwcrmDragDropDemoModule,

        CwcrmDialogDemoModule,
        CwcrmConfirmDialogDemoModule,
        CwcrmLightboxDemoModule,
        CwcrmTooltipDemoModule,
        CwcrmOverlayPanelDemoModule,
        CwcrmSideBarDemoModule,

        CwcrmDataTableDemoModule,
        CwcrmTableDemoModule,
        CwcrmDataGridDemoModule,
        CwcrmDataListDemoModule,
        CwcrmDataViewDemoModule,
        CwcrmDataScrollerDemoModule,
        CwcrmScheduleDemoModule,
        CwcrmOrderListDemoModule,
        CwcrmPickListDemoModule,
        CwcrmTreeDemoModule,
        CwcrmTreeTableDemoModule,
        CwcrmPaginatorDemoModule,
        CwcrmOrgChartDemoModule,
        CwcrmGmapDemoModule,
        CwcrmCarouselDemoModule,
        CwcrmProgressSpinnerDemoModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CwcrmprimengModule {}
