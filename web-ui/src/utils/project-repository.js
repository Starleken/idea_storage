const data = [{
    id: 1,
    name: 'IdeaStorage',
    idea: 'Платформа для структурирования идей и четкого планирования продуктов',
    shortDescription: 'Короткое описание продукта',
    fullDescription: 'Полное описание бля',
    reasonForPurchase: 'Покупайте продукт пж',
    price: 1000,
    createdAt: 1672372131,
    expiredAt: 1772372131,
    finishedAt:1772372134,
    isVisible: true
},
    {
        id: 2,
        name: 'Offender',
        idea: 'Игра про становление преступником',
        shortDescription: 'Короткое описание идеи продукта',
        fullDescription: 'Полное описание бля',
        reasonForPurchase: 'Покупайте продукт пж',
        price: 1000,
        createdAt: 1672372131,
        expiredAt: 1772372131,
        finishedAt:1772372134,
        isVisible: true
    }
]

function findAll() {
    return data
}

function findById(id = 0) {
    return data.find(x=>x.id=== id)
}


export {findAll, findById}