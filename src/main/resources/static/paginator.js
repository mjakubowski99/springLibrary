
class Paginator{

    constructor(dataContainer, navigationContainer, url, fields) {
        this.currentPage = 0;
        this.dataContainer = dataContainer;
        this.navigationContainer = navigationContainer;
        this.url = url;
        this.pagesCount = 0;
        this.fields = fields;
    }

    makeRow(row){
        const tr = document.createElement('tr');
        const th = document.createElement('th');
        th.setAttribute('scope', 'row');
        th.innerText = row.id;

        tr.appendChild(th);

        for(let i=0; i<this.fields.length; i++){
            let td = document.createElement('td');
            td.innerText = row[ this.fields[i] ];
            tr.appendChild(td);
        }

        return tr;
    }

    fetchPage(page){
        return fetch(this.url+"?page="+page).then( res => res.json() )
            .then( (data) => {
                this.dataContainer.innerHTML = '';
                for(let i=0; i<data['content'].length; i++){
                    this.dataContainer.appendChild( this.makeRow(data['content'][i]) );
                }

                if(page === 0 ){
                    this.pagesCount = data['totalPages'];
                }
            })
    }

    makeNavigationPageElement(pageNumber){
        const li = document.createElement('li');
        const a = document.createElement('a');

        li.setAttribute('class', 'page-item');
        a.setAttribute('class', 'page-link');
        a.setAttribute('href', '#');
        a.setAttribute('data-page', pageNumber-1);
        a.innerText = pageNumber;

        a.onclick = () => {
            if( this.currentPage !== a.dataset.page){
                this.fetchPage(a.dataset.page);
                this.currentPage = a.dataset.page;
            }
        }

        li.appendChild(a);

        return li;
    }

    initNavigation(){
        console.log(this.pagesCount);
        for(let i=0; i<this.pagesCount; i++){
            this.navigationContainer.appendChild( this.makeNavigationPageElement(i+1) );
        }
    }

    init(){
        this.fetchPage(0).then( () => this.initNavigation());
    }
}

