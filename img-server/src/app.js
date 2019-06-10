const http = require('http');
const app = require('./application');

// you can pass the parameter in the command line. e.g. node static_server.js 3000
const port = process.argv[2] || 9000;

const server = http.createServer(app);

server.listen(parseInt(port));

console.log(`Server listening on port ${port}`);