const api = require('../controllers/card-controller')

module.exports = (app) => {
    app.route('/cards/paginationAndSorting')
        .get(api.paginationAndSorting) // http://localhost:3000/card/paginationAndSorting?limit=5&page=0

    app.route('/cards')
        .get(api.findAll)
        .post(api.save)

    app.route('/cards/:id')
        .get(api.findById)
        .put(api.update)
        .delete(api.delete)
}