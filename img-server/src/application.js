const { parse } = require('url');
const { exists, readFile } = require('fs');
const path = require ('path');

// maps file extention to MIME types
const mimeType = {
  '.png' : 'image/png',
  '.jpg' : 'image/jpeg',
};

// you can pass the parameter in the command line. e.g. node static_server.js 3000
const port = process.argv[2] || 9000;

function handleGET(req, res) {
  // parse URL
  const parsedUrl = parse(req.url);
  // extract URL path
  // Avoid https://en.wikipedia.org/wiki/Directory_traversal_attack
  // e.g curl --path-as-is http://localhost:9000/../fileInDanger.txt
  // by limiting the path to current directory only
  const sanitizePath = path.normalize(parsedUrl.pathname).replace(/^(\.\.[\/\\])+/, '');

  let pathname = path.join(__dirname, sanitizePath);

  exists(pathname, function (exist) {
    if(!exist) {
      // if the file is not found, return 404
      res.statusCode = 404;
      res.end(`File ${pathname} not found!`);
      return;
    }

    // read file from file system
    readFile(pathname, (err, data) => {
      if (err) {
        res.statusCode = 500;
        res.end(`Error getting the file: ${err}.`);
      } else {
        // based on the URL path, extract the file extention. e.g. .js, .doc, ...
        const ext = path.parse(pathname).ext;
        // if the file is found, set Content-type and send data
        res.setHeader('Content-type', mimeType[ext]);
        res.end(data);
      }
    });
  });
}

function handlePOST(req, res) {

}

module.export = function (req, res) {
  console.log(`${req.method} ${req.url}`);

  switch (req.method) {
    case 'GET' : handleGET(req, res);  break;
    case 'POST': handlePOST(req, res); break;
    default: break;
  }
}