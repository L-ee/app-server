<div class="main grid_10" id="aboutContent">
    <div id="performance" class="performance right-container p20bs">
        <h1 class="rrdcolor-blue-text">业绩报告</h1>
        <p class="pinfo">红马金融以公平、互助、透明、分享的态度，从2012年开始，在每个季度结束后都为投资人、借款人、以及关心我们平台的普通用户，提供红马金融最真实、有效的业绩数据分析报告。</p>
        <div class="performance-list">
            <ul class="ui-list ui-list-m ui-list-news narrow mt10">
            <#list items as itemValue>
                <li class="ui-list-item fn-clear">
                    <p class="fn-left field decoration"></p>
                    <p class="fn-left field text color-silver-text fn-text-overflow"> <a class="title text-big rrd-dimgray" href="" target="_blank">${itemValue.title}</a> </p>
                    <p class="fn-left field date color-darkgray-text">${itemValue.publishTime}</p>
                </li>
            </#list>
            </ul>
        </div>
        <div class="mt10 ui-pagination simple-pagination right-ul-page .ui-pagination" id="news-list-pagination">
            <ul>
                <li class="active"><span class="current prev">Prev</span></li>
                <li class="active"><span class="current">1</span></li>
                <li><a href="#page-2" class="page-link">2</a></li>
                <li><a href="#page-2" class="page-link next">Next</a></li>
            </ul>
        </div>
    </div>
</div>