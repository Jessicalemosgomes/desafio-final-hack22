const neDB = require('../configurations/database')
const api = {}

api.findAll = (request, response) => {
    neDB.find({}).sort({ name: 1 }).exec((exception, cards) => {
        if (exception) {
            jsonAsError = { message: exception }
            response.json(jsonAsError)
            response.status(exception.status | 501).end()
        }
        response.json(cards)
    })
}

api.findById = function (request, response) {
    const id = request.params.id
    neDB.findOne({ _id: id }, function (exception, card) {
        if (exception) {
            jsonAsError = { message: exception }
            response.json(jsonAsError)
            response.status(exception.status | 501).end()
        }
        if(card) {
            response.json(card)
        } else {
            response.sendStatus(404)
        }
    })
}

api.save = (request, response) => {
    const canonical = request.body
    neDB.insert(canonical, (exception, card) => {
        if (exception) {
            jsonAsError = { message: exception }
            response.json(jsonAsError)
            response.status(exception.status | 501).end()
        }
        response.status(201)
        response.json(card)
    })
}

api.update = function (request, response) {
    const primaryKey = request.params.id
    const canonical = request.body
    neDB.update({ _id: primaryKey }, canonical, (exception, hadUpdated) => {
        if (exception) {
            jsonAsError = { message: exception }
            response.json(jsonAsError)
            response.status(exception.status | 501).end()
        }
        if (hadUpdated) {
            canonical._id = primaryKey
            response.json(canonical)
        } else {
            response.sendStatus(404)
        }
    })
}

api.delete = function (request, response) {
    const primaryKey = request.params.id
    neDB.remove({ _id: primaryKey }, {}, (exception, hadDeleted) => {
        if (exception) {
            jsonAsError = { message: exception }
            response.json(jsonAsError)
            response.status(exception.status | 501).end()
        }
        if (hadDeleted) {
            response.json({ 'message': `Card id '${primaryKey}' had been deleted successfuly` })
            response.status(200)
        } else {
            response.sendStatus(404)
        }
    })
}

api.paginationAndSorting = (request, response) => {
    const page = request.query.page
    const limit = request.query.size

    neDB.find({}).sort({ name: 1 }).skip(page).limit(limit).exec((exception, cards) => {
        if (exception) {
            jsonAsError = { message: exception }
            response.json(jsonAsError)
            response.status(exception.status | 501).end()
        }
        response.json(cards)
    })
}

module.exports = api